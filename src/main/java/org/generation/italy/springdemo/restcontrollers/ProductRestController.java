package org.generation.italy.springdemo.restcontrollers;

import jakarta.transaction.Transactional;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.ProductFilter;
import org.generation.italy.springdemo.restdtos.ProductRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product") //Questo è il path di base, verrà richiesto per ogni metodo + quello specificato nel metodo
@CrossOrigin(origins = "*")
public class ProductRestController {
    private StoreService storeService;

    @Autowired // builder
    public ProductRestController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<?> search(@RequestParam(required = false) Integer id,
                                    @RequestParam(required = false) String name,
                                    @RequestParam(required = false) Integer supplierId,
                                    @RequestParam(required = false) Integer categoryId,
                                    @RequestParam(required = false) BigDecimal minPrice,
                                    @RequestParam(required = false) BigDecimal maxPrice,
                                    @RequestParam(required = false) Boolean orderById,
                                    @RequestParam(required = false) Boolean orderByName,
                                    @RequestParam(required = false) Boolean orderByPrice) throws DataException {
        List<Product> result;
        if(orderById != null && orderById.equals(true)){
            result =storeService.findAllOrderByproductId();
        }else
        if(orderByName != null && orderByName.equals(true)){
            result =storeService.findAllOrderByproductName();
        }else
        if(orderByPrice != null && orderByPrice.equals(true)){
            result =storeService.findAllOrderByunitPrice();
        }else{
            ProductFilter pf = new ProductFilter(id,name,supplierId,categoryId,minPrice,maxPrice);
            result = storeService.filterProductsByNameAndSupplierIDAndCategoryIdAndPriceBetween(pf);
        }
        List<ProductRestDto> dtoList = ProductRestDto.listToDtoList(result);
        return (dtoList.isEmpty())?ResponseEntity.notFound().build() :ResponseEntity.ok(dtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) throws  DataException{
        boolean deleted = storeService.deleteProduct(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProductRestDto> createProduct(@RequestBody ProductRestDto dto) throws DataException, EntityNotFoundException {
        Product p = dto.toProduct();
        storeService.saveProduct(p, dto.getSupplierId(), dto.getCategoryId());
        ProductRestDto saved = ProductRestDto.toDto(p);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getProductId())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }


    @PutMapping
    @Transactional
    public ResponseEntity<ProductRestDto> updateProduct(@RequestBody ProductRestDto dto) throws DataException {
        Optional<Product> productOnDb = storeService.findProductById(dto.getProductId());
        if(productOnDb.isPresent()){ // controlla se c'è un prodotto con quel id
            if(!dto.equalToProduct(productOnDb.get())){
                ProductRestDto saved = ProductRestDto.toDto(storeService.saveProduct(dto.toProduct(), dto.getSupplierId(), dto.getCategoryId()));
                return ResponseEntity.ok(saved);
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }
}

package org.generation.italy.springdemo.restcontrollers;

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
public class ProductRestController {
    private StoreService storeService;

    @Autowired // builder
    public ProductRestController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() throws DataException{
            List<ProductRestDto> ps =  storeService.findAllProducts().stream().map(ProductRestDto::toDto).toList();
            return ResponseEntity.ok(ps);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) throws DataException {
        Optional<Product> p = storeService.findProductById(id);
        if (p.isPresent()) {
            var productDto = ProductRestDto.toDto(p.get());
            return ResponseEntity.ok(productDto);
        }
        return ResponseEntity.notFound().build();
    }

    // filter by the first param found
//    @GetMapping
//    public ResponseEntity<?> search(@RequestParam(required = false) Integer id,
//                                    @RequestParam(required = false) String name,
//                                    @RequestParam(required = false) Integer supplierId,
//                                    @RequestParam(required = false) Integer categoryId,
//                                    @RequestParam(required = false) Integer minPrice,
//                                    @RequestParam(required = false) Integer maxPrice) throws DataException {
//
//        if (id != null) {
//            return storeService.findProductById(id)
//                    .map(p -> ResponseEntity.ok(ProductRestDto.toDto(p)))
//                    .orElse(ResponseEntity.notFound().build());
//        }
//
//        if (name != null && !name.isEmpty()) {
//            return storeService.findByProductName(name)
//                    .map(p -> ResponseEntity.ok(ProductRestDto.toDto(p)))
//                    .orElse(ResponseEntity.notFound().build());
//        }
//
//        if (supplierId != null) {
//            var list = ProductRestDto.listToDtoList(storeService.findBySuppId(supplierId));
//            return list.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(list);
//        }
//
//        if (categoryId != null) {
//            var list = ProductRestDto.listToDtoList(storeService.findByCatId(categoryId));
//            return list.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(list);
//        }
//
//        if (minPrice != null && maxPrice != null) {
//            var list = ProductRestDto.listToDtoList(storeService.findProductByUnitPriceBetween(minPrice, maxPrice));
//            return list.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(list);
//        }
//
//        var all = ProductRestDto.listToDtoList(storeService.findAllProducts());
//        return ResponseEntity.ok(all);
//    }

    @GetMapping
    public ResponseEntity<?> search(@RequestParam(required = false) Integer id,
                                    @RequestParam(required = false) String name,
                                    @RequestParam(required = false) Integer supplierId,
                                    @RequestParam(required = false) Integer categoryId,
                                    @RequestParam(required = false) BigDecimal minPrice,
                                    @RequestParam(required = false) BigDecimal maxPrice) throws DataException {
        ProductFilter pf = new ProductFilter(id,name,supplierId,categoryId,minPrice,maxPrice);
        List<Product> result = storeService.filterProductsByNameAndSupplierIDAndCategoryIdAndPriceBetween(pf);
        List<ProductRestDto> dtoList = ProductRestDto.listToDtoList(result);
        return (dtoList.isEmpty())?ResponseEntity.ok(dtoList):ResponseEntity.notFound().build();
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
    public ResponseEntity<ProductRestDto> updateProduct(@RequestBody ProductRestDto dto) throws DataException {
        Optional<Product> productOnDb = storeService.findProductById(dto.getProductId());
        if(productOnDb.isPresent()){ // controlla se c'è un prodotto con quel id
            ProductRestDto saved = ProductRestDto.toDto(storeService.saveProduct(dto.toProduct(), dto.getSupplierId(), dto.getCategoryId()));
            return ResponseEntity.ok(saved);
        }
        return ResponseEntity.notFound().build();
    }
}

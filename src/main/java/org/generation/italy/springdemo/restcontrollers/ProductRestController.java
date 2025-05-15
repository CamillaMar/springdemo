package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.models.searchcriteria.ProductFilterCriteria;
import org.generation.italy.springdemo.restdtos.ProductRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.math.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {
    private StoreService storeService;

    @Autowired
    public ProductRestController(StoreService storeService){
        this.storeService = storeService;
    }

//    @GetMapping
//    public ResponseEntity<?> getAllProducts() throws DataException{
//            List<ProductRestDto> ps =  storeService.findAllProducts().stream().map(ProductRestDto::toDto).toList();
//            // return ResponseEntity.status(200).body(ps);
//            return ResponseEntity.ok(ps);
//    }
    @GetMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> findProducts( @RequestParam(required = false) Integer topN,
                                           @RequestParam(required = false) Integer supplierId,
                                           @RequestParam(required = false) Integer categoryId,
                                           @RequestParam(required = false) BigDecimal minPrice,
                                           @RequestParam(required = false) BigDecimal maxPrice,
                                           @RequestParam(required = false) Integer discontinued) throws DataException{
        if(topN != null && topN > 0) {
            List<ProductRestDto> products = storeService.findMostExpensiveProducts(topN).stream().map(ProductRestDto::toDto).toList();
            return ResponseEntity.ok(products);
        }
        ProductFilterCriteria filters = new ProductFilterCriteria(supplierId, categoryId, minPrice, maxPrice, discontinued);
        List<ProductRestDto> products = storeService.searchProduct(filters).stream().map(ProductRestDto::toDto).toList();
        return ResponseEntity.ok(products);
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
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody ProductRestDto newProduct) throws DataException, EntityNotFoundException {
        if(id != newProduct.getProductId()){
            return ResponseEntity.badRequest().body("l'id del path de del dto non corrispondono");
        }

        Optional<Product> op = storeService.findProductById(newProduct.getProductId());
        if(op.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Product p = newProduct.toProduct();
        int categoryId = newProduct.getCategoryId();
        int supplierId = newProduct.getSupplierId();

        storeService.updateProduct(p, categoryId, supplierId);
        return ResponseEntity.ok().build();
    }
}

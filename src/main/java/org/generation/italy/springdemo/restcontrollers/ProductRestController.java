package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.ProductRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    private StoreService storeService;

    @Autowired
    public ProductRestController(StoreService storeService){
        this.storeService = storeService;
    }
    @GetMapping
    public ResponseEntity<?> getAllProducts() throws DataException{
            List<ProductRestDto> ps =  storeService.findAllProducts().stream().map(ProductRestDto::toDto).toList();
            // return ResponseEntity.status(200).body(ps);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) throws  DataException{
        boolean deleted = storeService.deleteProduct(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable int id, @RequestBody ProductRestDto dto) throws DataException, EntityNotFoundException {
        Optional<Product> optionalProduct= storeService.findProductById(id);
        if(optionalProduct.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        if(id!=dto.getProductId()){
            return ResponseEntity.badRequest().body("Resource id and dto id do not match");
        }
        storeService.updateProduct(optionalProduct.get(), dto);
        return ResponseEntity.ok(ProductRestDto.toDto(optionalProduct.get()));
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
}

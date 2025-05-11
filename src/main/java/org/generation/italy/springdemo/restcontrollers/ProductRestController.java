package org.generation.italy.springdemo.restcontrollers;

import org.apache.catalina.authenticator.jaspic.PersistentProviderRegistrations;
import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.ProductRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    @GetMapping
    public ResponseEntity<?> getAllProducts() throws DataException{
            List<ProductRestDto> ps =  storeService.findAllProducts().stream().map(ProductRestDto::toDto).toList();
//            return ResponseEntity.status(200).body(ps);
            return ResponseEntity.ok(ps);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) throws DataException{
        Optional<Product> p = storeService.findProductById(id);
        if(p.isPresent()){
            ProductRestDto productDto = ProductRestDto.toDto(p.get());
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
    public ResponseEntity<ProductRestDto> createProduct(@RequestBody ProductRestDto dto) throws DataException{
        Product p = dto.toProduct();
        storeService.saveProduct(p,dto.getSupplierId(),dto.getCategoryId());
        ProductRestDto saved = ProductRestDto.toDto(p);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getProductId()).toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody ProductRestDto dto) throws DataException{
        Optional<Product> op = storeService.findProductById(id);
        if(op.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        if(dto.getProductId() != id){
            return ResponseEntity.badRequest().body("Id risorse e Id del Dto non corrispondono :(, ritenta! :)");
        }
        Product p = dto.toProduct();
        storeService.updateProduct(p, dto.getSupplierId(), dto.getCategoryId());
        return ResponseEntity.ok(ProductRestDto.toDto(op.get()));
    }
}

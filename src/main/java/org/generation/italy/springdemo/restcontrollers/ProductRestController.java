package org.generation.italy.springdemo.restcontrollers;

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

import java.math.BigDecimal;
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
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody ProductRestDto dto) throws DataException, EntityNotFoundException{
        if(id != dto.getProductId()){
            return ResponseEntity.badRequest().body("L'id risorsa e id del dto non corrispondono.");
        }
        Product np = dto.toProduct();
        Product updated = storeService.updateProduct(id, np, dto.getSupplierId(), dto.getCategoryId());
        ProductRestDto newDto = ProductRestDto.toDto(updated);
        return ResponseEntity.ok().body(newDto);
    }

    @GetMapping()
    public ResponseEntity<List<ProductRestDto>> searchProducts(@PathVariable Integer categoryId,
                                            @PathVariable Integer supplierId,
                                            @PathVariable BigDecimal minPrice,
                                            @PathVariable BigDecimal maxPrice) throws DataException {
        var dtos = storeService.searchProducts(categoryId, supplierId, minPrice, maxPrice).
                stream().map(ProductRestDto::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

}

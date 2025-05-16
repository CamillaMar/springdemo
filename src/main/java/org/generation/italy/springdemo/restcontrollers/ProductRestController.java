package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.entities.Product;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.exceptions.EntityNotFoundException;
import org.generation.italy.springdemo.models.searchcriteria.ProductFilterCriteria;
import org.generation.italy.springdemo.models.services.StoreService;
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
@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
public class ProductRestController {
    private StoreService storeService;

    @Autowired
    public ProductRestController(StoreService storeService) {
        this.storeService = storeService;
    }
    
    @GetMapping
    public ResponseEntity<List<ProductRestDto>> getAllProducts(@RequestParam(required = false) Integer topN,
                                                               @RequestParam(required = false) Integer categoryId,
                                                               @RequestParam(required = false) Integer supplierId,
                                                               @RequestParam(required = false) BigDecimal minPrice,
                                                               @RequestParam(required = false) BigDecimal maxPrice) throws DataException {
        List<ProductRestDto> productDtos = null;

        if (topN != null) {
           productDtos = storeService.findMostExpensiveProducts(topN).stream()
                   .map(ProductRestDto::toDto)
                   .toList();
        } else {
            var filters = new ProductFilterCriteria(categoryId, supplierId, minPrice, maxPrice);
            productDtos = storeService.searchProducts(filters).stream()
                    .map(ProductRestDto::toDto)
                    .toList();
        }

        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRestDto> getProductById(@PathVariable int id) throws DataException {
        Optional<Product> op = storeService.findProductById(id);
        if (op.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var productDto = ProductRestDto.toDto(op.get());
        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable int id) throws DataException {
        boolean deleted = storeService.deleteProductById(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ProductRestDto> createProduct(@RequestBody ProductRestDto dto) throws DataException, EntityNotFoundException {
        Product p = dto.toProduct();
        storeService.saveProduct(p, dto.getSupplierId(), dto.getCategoryId());
        ProductRestDto savedProduct = ProductRestDto.toDto(p);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getProductId())
                .toUri();

        return ResponseEntity.created(location).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductRestDto> updateProduct(@PathVariable int id, @RequestBody ProductRestDto dto) throws DataException, EntityNotFoundException {
        if (dto.getProductId() != id) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Product> op = storeService.findProductById(id);
        if (op.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        storeService.updateProduct(dto.toProduct(), dto.getSupplierId(), dto.getCategoryId());
        return ResponseEntity.ok(ProductRestDto.toDto(op.get()));
    }
}

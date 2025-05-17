package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.entities.Customer;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.CustomerRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {
    private StoreService storeService;

    @Autowired
    public CustomerRestController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> findById(@PathVariable int id) throws DataException {
        Optional<Customer> c = storeService.findCustomerById(id);
        if (c.isPresent()) {
            CustomerRestDto customerDto = CustomerRestDto.toDto(c.get());
            return ResponseEntity.ok(customerDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findByMostOrders")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> findByMostOrders() throws DataException {
        Optional<Customer> c = storeService.findCustomerByMostOrders();
        if (c.isPresent()) {
            CustomerRestDto customerDto = CustomerRestDto.toDto(c.get());
            return ResponseEntity.ok(customerDto);
        }
        return ResponseEntity.notFound().build();
    }

//    @GetMapping
//    @CrossOrigin(origins = "*")
//    public ResponseEntity<?> findCategory(@RequestParam(required = false) Integer topN,
//                                          @RequestParam(required = false) Integer supplierId,
//                                          @RequestParam(required = false) Integer categoryId,
//                                          @RequestParam(required = false) BigDecimal minPrice,
//                                          @RequestParam(required = false) BigDecimal maxPrice,
//                                          @RequestParam(required = false) Integer discontinued) throws DataException {
//        if(topN != null && topN > 0) {
//            List<ProductRestDto> products = storeService.findMostExpensiveProducts(topN).stream().map(ProductRestDto::toDto).toList();
//            return ResponseEntity.ok(products);
//        }else if(topN != null){
//            return ResponseEntity.ok(new ArrayList<ProductRestDto>());
//        }
//        ProductFilterCriteria filters = new ProductFilterCriteria(supplierId, categoryId, minPrice, maxPrice, discontinued);
//        List<ProductRestDto> products = storeService.searchProduct(filters).stream().map(ProductRestDto::toDto).toList();
//        return ResponseEntity.ok(products);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteById(@PathVariable int id) throws  DataException{
//        boolean deleted = storeService.deleteProduct(id);
//        if(deleted){
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @PostMapping
//    @CrossOrigin(origins = "*")
//    public ResponseEntity<ProductRestDto> createCategory(@RequestBody ProductRestDto dto) throws DataException, EntityNotFoundException {
//        Product p = dto.toProduct();
//        storeService.saveProduct(p, dto.getSupplierId(), dto.getCategoryId());
//        ProductRestDto saved = ProductRestDto.toDto(p);
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(saved.getProductId())
//                .toUri();
//        return ResponseEntity.created(location).body(saved);
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateCategory(@PathVariable int id, @RequestBody ProductRestDto newProduct) throws DataException, EntityNotFoundException {
//        if(id != newProduct.getProductId()){
//            return ResponseEntity.badRequest().body("l'id del path de del dto non corrispondono");
//        }
//
//        Optional<Product> op = storeService.findProductById(newProduct.getProductId());
//        if(op.isEmpty()){
//            return ResponseEntity.notFound().build();
//        }
//
//        Product p = newProduct.toProduct();
//        int categoryId = newProduct.getCategoryId();
//        int supplierId = newProduct.getSupplierId();
//
//        storeService.updateProduct(p, categoryId, supplierId);
//        return ResponseEntity.ok().build();
//    }
}

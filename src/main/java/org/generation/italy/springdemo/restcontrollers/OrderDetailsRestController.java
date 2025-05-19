package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.entities.OrderDetails;
import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.OrderDetailsRestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/orderdetails")
public class OrderDetailsRestController {
    private StoreService storeService;

    public OrderDetailsRestController(StoreService storeService) {
        this.storeService = storeService;
    }

    //Qui ci sono dei problemi che non riesco a risolvere
    public ResponseEntity<?> findOrderDetailsByUnitPrice(BigDecimal unitprice) throws DataException {
        List<OrderDetailsRestDto> listOrderDetails = storeService.findOrderDetailsByUnitPrice(unitprice).stream().map(OrderDetailsRestDto::toDto).toList();
        return ResponseEntity.ok(listOrderDetails);
    }
}

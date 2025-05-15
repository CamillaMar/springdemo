package org.generation.italy.springdemo.restcontrollers;

import org.generation.italy.springdemo.models.exceptions.DataException;
import org.generation.italy.springdemo.models.searchCriteria.OrderFilterCriteria;
import org.generation.italy.springdemo.models.services.StoreService;
import org.generation.italy.springdemo.restdtos.OrderRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/orders")
public class OrderRestController {
    private StoreService storeService;

    @Autowired
    public OrderRestController(StoreService storeService){
        this.storeService = storeService;
    }


    @GetMapping
    public ResponseEntity<?> getAllOrderByFilters(@RequestParam(required = false) Integer custId,
                                                  @RequestParam (required = false) Integer empId,
                                                  @RequestParam (required = false) LocalDateTime orderDate,
                                                  @RequestParam (required = false) BigDecimal freight,
                                                  @RequestParam (required = false) String shipCountry) throws DataException{
        OrderFilterCriteria ofc = new OrderFilterCriteria(custId,empId,orderDate,freight,shipCountry);
        List<OrderRestDto> orderFiltrated = storeService.searchOrders(ofc).stream().map(OrderRestDto:: toDto).toList();
        return ResponseEntity.ok(orderFiltrated);
    }

}
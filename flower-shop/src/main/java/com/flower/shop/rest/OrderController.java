package com.flower.shop.rest;

import com.flower.shop.application.domain.services.ClientService;
import com.flower.shop.application.domain.services.OrderService;
import com.flower.shop.application.dto.AddressDto;
import com.flower.shop.application.dto.OrderDto;
import com.flower.shop.application.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderService orderService;

    @CrossOrigin
    @GetMapping()
    public ResponseEntity<List<OrderDto>> getOrders(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        List<OrderDto> orders = orderService.getOrders(pageNo, pageSize);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/{client_id}")
    public ResponseEntity<OrderDto> createOrder(@RequestParam UUID clientId, AddressDto address) {
        if(!addressIsValid(address) || !clientService.clientExists(clientId))
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(orderService.createOrder(clientId, address));
    }

    private boolean addressIsValid(AddressDto address) {
        //fixme more validation
        if(address.getCity().isEmpty()
                && address.getStreet().isEmpty()
                && address.getCountry().isEmpty()
                && address.getPostalCode().isEmpty())
            return false;
        return true;
    }
}

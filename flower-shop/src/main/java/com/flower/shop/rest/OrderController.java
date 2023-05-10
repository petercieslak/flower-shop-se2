package com.flower.shop.rest;

import com.flower.shop.application.domain.services.ClientService;
import com.flower.shop.application.domain.services.OrderService;
import com.flower.shop.application.dto.AddressDto;
import com.flower.shop.application.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/orders/{client_id}")
public class OrderController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderService orderService;

    @PostMapping
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

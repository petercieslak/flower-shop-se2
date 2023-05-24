package com.flower.shop.rest;

import com.flower.shop.application.domain.services.ClientService;
import com.flower.shop.application.domain.services.OrderService;
import com.flower.shop.application.dto.AddressDto;
import com.flower.shop.application.dto.ModifyOrderDto;
import com.flower.shop.application.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/{client_id}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable("client_id") UUID clientId, AddressDto address) {
        if(!addressIsValid(address) || !clientService.clientExists(clientId))
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(orderService.createOrder(clientId, address));
    }

    @CrossOrigin
    @PutMapping("/{order_id}")
    public ResponseEntity<Void> modifyOrder(@PathVariable("order_id") UUID orderId, @RequestBody ModifyOrderDto orderdto) {
        if(!addressIsValid(orderdto.getDeliveryAddress()))
            return ResponseEntity.badRequest().build();

        orderService.modifyOrder(orderId, orderdto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private boolean addressIsValid(AddressDto address) {
        if(address.getCity() == null ||
                address.getStreet() == null ||
                address.getCountry() == null ||
                address.getPostalCode() == null)
            return false;
        return true;
    }
}

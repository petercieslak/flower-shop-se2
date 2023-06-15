package com.flower.shop.rest;

import static com.flower.shop.rest.util.RegexMatcher.noNumbersMatcher;
import static com.flower.shop.rest.util.RegexMatcher.postalCodeMatcher;

import com.flower.shop.application.domain.services.ClientService;
import com.flower.shop.application.domain.services.OrderService;
import com.flower.shop.application.dto.*;
import com.flower.shop.data.dao.ClientDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/orders")
@Slf4j
public class OrderController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderService orderService;

    @CrossOrigin
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<OrderDto>> getOrders(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        List<OrderDto> orders = orderService.getOrders(pageNo, pageSize);
        return ResponseEntity.ok(orders);
    }

    @CrossOrigin
    @GetMapping("/{client_id}")
    public ResponseEntity<List<OrderDto>> getClientOrders(@PathVariable("client_id") UUID clientId,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        if(!clientService.clientExists(clientId)){
            return ResponseEntity.badRequest().build();
        }
        List<OrderDto> orders = orderService.getClientOrders(pageNo, pageSize, clientId);
        return ResponseEntity.ok(orders);
    }

    @CrossOrigin
    @PostMapping("/{client_id}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable("client_id") UUID clientId,
                                                @RequestBody AddressDto address) {
        if(!addressIsValid(address))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(!clientService.clientExists(clientId))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(orderService.createOrder(clientId, address), HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping("/{order_id}")
    public ResponseEntity<Void> modifyOrder(@PathVariable("order_id") UUID orderId, @RequestBody ModifyOrderDto orderdto) {
        if(!addressIsValid(orderdto.getDeliveryAddress()))
            return ResponseEntity.badRequest().build();

        orderService.modifyOrder(orderId, orderdto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @CrossOrigin
    @PutMapping("/{order_id}/status")
    public ResponseEntity<Void> modifyOrderStatus(@PathVariable("order_id") UUID orderId, @RequestBody ModifyOrderStatusDto orderdto) {
        orderService.modifyOrderStatus(orderId, orderdto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private boolean addressIsValid(AddressDto address) {
        if(address.getCity() == null ||
                address.getStreet() == null ||
                address.getCountry() == null ||
                address.getPostalCode() == null)
            return false;
        if(!postalCodeMatcher(address.getPostalCode())) {
            log.error("Invalid postal code: " + address.getPostalCode());
            return false;
        }
        if(!noNumbersMatcher(address.getCity())) {
            log.error("Invalid city: " + address.getCity());
            return false;
        }
        if(!noNumbersMatcher(address.getCountry())) {
            log.error("Invalid country: " + address.getCountry());
            return false;
        }
        return true;
    }
}

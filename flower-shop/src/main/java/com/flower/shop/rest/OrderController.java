package com.flower.shop.rest;

import static com.flower.shop.rest.util.RegexMatcher.noNumbersMatcher;
import static com.flower.shop.rest.util.RegexMatcher.postalCodeMatcher;

import com.flower.shop.application.domain.services.ClientService;
import com.flower.shop.application.domain.services.OrderService;
import com.flower.shop.application.dto.AddressDto;
import com.flower.shop.application.dto.OrderDto;
import com.flower.shop.data.dao.ClientDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/orders")
@Slf4j
public class OrderController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/{client_id}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable("client_id") UUID clientId,
                                                @RequestBody AddressDto address) {
        if(!addressIsValid(address))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(!clientService.clientExists(clientId))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(orderService.createOrder(clientId, address), HttpStatus.CREATED);
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

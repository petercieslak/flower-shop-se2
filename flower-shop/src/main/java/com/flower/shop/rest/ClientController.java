package com.flower.shop.rest;


import com.flower.shop.application.domain.services.ClientService;
import com.flower.shop.application.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/clients")
@Slf4j
public class ClientController {

    @Autowired
    private ClientService clientService;


    @CrossOrigin
    @PutMapping("/{client_id}")
    public ResponseEntity<Void> modifyNewsletter(@PathVariable("client_id") UUID orderId, @RequestBody Boolean hasNewsletter) {
        clientService.changeNewsletter(orderId, hasNewsletter);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin
    @GetMapping("/{client_id}")
    public ResponseEntity<Boolean> getNewsletter(@PathVariable("client_id") UUID clientId) {
        return ResponseEntity.ok(clientService.getNewsletter(clientId));
    }

}

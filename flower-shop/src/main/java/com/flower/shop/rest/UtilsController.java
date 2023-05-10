package com.flower.shop.rest;

import com.flower.shop.application.domain.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/utils")
public class UtilsController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/client_id")
    public ResponseEntity<UUID> getClientId(String clientMail) {
        return ResponseEntity.ok(clientService.getClientIdByMail(clientMail));
    }
}

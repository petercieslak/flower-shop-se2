package com.flower.shop.rest;

import com.flower.shop.application.domain.services.ClientService;
import com.flower.shop.data.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/utils")
public class UtilsController {

    @Autowired
    private ClientService clientService;

    @CrossOrigin
    @GetMapping("/client_id")
    public ResponseEntity<UUID> getClientId(String clientMail) {
        return ResponseEntity.ok(clientService.getClientIdByMail(clientMail));
    }

    @CrossOrigin
    @GetMapping("/{clientID}")
    public ResponseEntity<String> getUserFullname(@PathVariable String clientID) {
        UUID ID = UUID.fromString(clientID);
//        if(clientService.findUser(ID)) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
        Person p = clientService.findUser(ID);
        return ResponseEntity.ok(p.getFirstName() + ' ' + p.getLastName());
    }
}

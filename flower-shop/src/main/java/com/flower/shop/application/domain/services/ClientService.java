package com.flower.shop.application.domain.services;

import com.flower.shop.data.dao.ClientDAO;
import com.flower.shop.data.models.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class ClientService {

    @Autowired
    private ClientDAO clientRepository;

    public boolean clientExists(UUID clientId) {
        if(clientRepository.existsById(clientId))
            return true;
        else {
            log.info("Client with UUID " + clientId + " does not exist!");
            return false;
        }
    }

    public UUID getClientIdByMail(String clientMail) {
        return clientRepository.findByEmail(clientMail).get().getId();
    }
    public UUID getIds() {
        return clientRepository.findAll().get(0).getId();
    }

    public Client getClientByEmail(String email) {
        return (Client) clientRepository.findByEmail(email).get();
    }
}

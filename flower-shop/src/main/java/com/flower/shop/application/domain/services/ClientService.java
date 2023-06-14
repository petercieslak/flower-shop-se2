package com.flower.shop.application.domain.services;

import com.flower.shop.data.dao.ClientDAO;
import com.flower.shop.data.dao.ClientNewDAO;
import com.flower.shop.data.models.Order;
import com.flower.shop.data.models.Person;
import com.flower.shop.data.models.Client;
import com.flower.shop.data.models.Product;
import com.flower.shop.data.models.integration.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class ClientService {

    @Autowired
    private ClientDAO clientRepository;

    @Autowired
    private ClientNewDAO clientNewRepository;

    public boolean clientExists(UUID clientId) {
        if(clientRepository.existsById(clientId))
            return true;
        else {
            log.info("Client with UUID " + clientId + " does not exist!");
            return false;
        }
    }

    public Person findUser(UUID clientId){
        return clientRepository.findById(clientId).get();
    }

    public Client findClient(UUID clientId){
        return clientNewRepository.findById(clientId).get();
    }

    public void changeNewsletter(UUID clientId, boolean hasNewsletter){
        Client modifiedClient = findClient(clientId);
        modifiedClient.setHasNewsletterOn(hasNewsletter);
        clientNewRepository.save(modifiedClient);
    }

    public boolean getNewsletter(UUID clientId){
        return findClient(clientId).getHasNewsletterOn();
    }


    public UUID getClientIdByMail(String clientMail) {
        return clientRepository.findByEmail(clientMail).get().getId();
    }
}

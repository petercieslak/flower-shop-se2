package com.flower.shop.rest.integration;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

public class BasketClient extends IntegrationClient{

    private final String BASKET_URI;

    public BasketClient(RestTemplate restTemplate, HttpHeaders headers, String URI) {
        super(restTemplate, headers);
        BASE_URI = URI;
        BASKET_URI = URI + "/basket";
    }

    public ResponseEntity<String> addProduct(UUID productID, String jwtToken) {
        headers.set("Authorization", "Bearer " + jwtToken);
        HttpEntity<String> request = new HttpEntity<>("{ \"productID\": " + "\"" + productID + "\"" + ",\"quantity\": 1 }", headers);
        return restTemplate.postForEntity(BASKET_URI,
                request,
                String.class);
    }
}

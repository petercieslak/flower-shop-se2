package com.flower.shop.rest.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flower.shop.data.models.integration.Product;
import lombok.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ProductsClient extends  IntegrationClient {

    private final String PRODUCTS_URI;

    public ProductsClient(RestTemplate restTemplate, HttpHeaders headers, String URI) {
        super(restTemplate, headers);
        BASE_URI = URI;
        PRODUCTS_URI = BASE_URI + "/products";
    }

    public Product getProduct(UUID productId) {
        return restTemplate.getForObject(PRODUCTS_URI + "/" + productId,
                Product.class);
    }

    public ResponseEntity<String> createProduct(Product product, String jwtToken) {
        headers.set("Authorization", "Bearer " + jwtToken);
        HttpEntity<Product> request = new HttpEntity<>(product, headers);
        return restTemplate.postForEntity(PRODUCTS_URI,
                request,
                String.class);
    }

}

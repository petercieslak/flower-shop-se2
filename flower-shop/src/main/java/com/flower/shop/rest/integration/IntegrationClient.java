package com.flower.shop.rest.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
public class IntegrationClient {
    protected RestTemplate restTemplate;

    protected HttpHeaders headers;

    protected String BASE_URI;

    public IntegrationClient(RestTemplate restTemplate, HttpHeaders headers) {
        this.restTemplate = restTemplate;
        this.headers = headers;
        headers.setContentType(MediaType.APPLICATION_JSON);
    }
}

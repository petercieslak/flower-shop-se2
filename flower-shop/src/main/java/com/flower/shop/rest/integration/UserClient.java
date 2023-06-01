package com.flower.shop.rest.integration;

import com.flower.shop.data.models.integration.User;
import com.flower.shop.data.models.integration.authentication.Authentication;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.TreeMap;

@Getter
@Setter
public class UserClient extends IntegrationClient {

    private final String USER_URI;

    public UserClient(RestTemplate restTemplate, HttpHeaders headers, String URI) {
        super(restTemplate, headers);
        BASE_URI = URI;
        USER_URI = BASE_URI + "/users";
    }

    public ResponseEntity<String> login(String email, String password) {
        HttpEntity<Authentication> request = new HttpEntity<>(getAuthentication(email, password), headers);
        return restTemplate.postForEntity(USER_URI + "/log_in",
                request,
                String.class);
    }

    public ResponseEntity<User> register(String userJson) {
        HttpEntity<String> request = new HttpEntity<>(userJson, headers);
        return restTemplate.postForEntity(USER_URI,
                request,
                User.class);
    }

    public ResponseEntity<User> register(User user) {
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        return restTemplate.postForEntity(USER_URI,
                request,
                User.class);
    }

    public ResponseEntity<String> updateNewsletter(String jwtToken, Boolean newsletterFlag) {
        headers.set("Authorization", "Bearer " + jwtToken);
        HttpEntity<String> request = new HttpEntity<>("{\"subscribed\": " + newsletterFlag.toString().toLowerCase()
                + " }", headers);
        return restTemplate.postForEntity(USER_URI + "/newsletter",
                request,
                String.class);
    }

    private Authentication getAuthentication(String email, String password) {
        return Authentication.builder()
                .username(email)
                .password(password)
                .build();
    }
}

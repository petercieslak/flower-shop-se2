package com.flower.shop.rest;

import com.flower.shop.application.authentication.AuthenticationService;
import com.flower.shop.application.authentication.util.AuthenticationRequest;
import com.flower.shop.application.authentication.util.AuthenticationResponse;
import com.flower.shop.application.authentication.util.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    private final String NAME_REGEX = "[a-zA-Z]+";

    private final String EMAIL_REGEX = ".+[@]{1}.+";


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        if(!validCredentials(request))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if(!service.userExists(request))
            return ResponseEntity.ok(service.register(request));
        return new ResponseEntity(HttpStatus.FORBIDDEN); //403 - user already exists
    }
    @PostMapping(value="/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    private Boolean validCredentials(RegisterRequest request) {
        List<String> credentials = new ArrayList<>();
        credentials.add(request.getFirstname());
        credentials.add(request.getLastname());
        for(String name : credentials)
            if(!nameSyntaxValid(name))
                return false;
        if(!emailSyntaxValid(request.getEmail()))
            return false;
        return true;
    }

    private Boolean nameSyntaxValid(String name) {
        if(name.length() > 0 && Pattern.matches(NAME_REGEX, name))
            return true;
        return false;
    }

    private Boolean emailSyntaxValid(String email) {
        if(email.length() > 0 && Pattern.matches(EMAIL_REGEX, email))
            return true;
        return false;
    }
}

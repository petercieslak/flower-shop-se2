package com.flower.shop.rest;

import static com.flower.shop.rest.util.RegexMatcher.emailMatcher;
import static com.flower.shop.rest.util.RegexMatcher.noNumbersMatcher;

import com.flower.shop.application.authentication.AuthenticationService;
import com.flower.shop.application.authentication.util.AuthenticationRequest;
import com.flower.shop.application.authentication.util.AuthenticationResponse;
import com.flower.shop.application.authentication.util.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        if(!validCredentials(request))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if(!service.userExists(request))
            return ResponseEntity.ok(service.register(request));
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
    @CrossOrigin
    @PostMapping(value="/log_in")
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
        if(name.length() > 0 && noNumbersMatcher(name))
            return true;
        return false;
    }

    private Boolean emailSyntaxValid(String email) {
        if(email.length() > 0 && emailMatcher(email))
            return true;
        return false;
    }
}

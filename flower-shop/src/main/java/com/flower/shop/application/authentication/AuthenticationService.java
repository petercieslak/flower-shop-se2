package com.flower.shop.application.authentication;

import com.flower.shop.data.dao.CartDAO;
import com.flower.shop.data.dao.ClientDAO;
import com.flower.shop.data.models.Cart;
import com.flower.shop.data.models.Client;
import com.flower.shop.application.authentication.util.AuthenticationRequest;
import com.flower.shop.application.authentication.util.AuthenticationResponse;
import com.flower.shop.application.authentication.util.RegisterRequest;
import com.flower.shop.data.models.Employee;
import com.flower.shop.data.models.Person;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationService {
    private final ClientDAO clientRepository;
    private final CartDAO cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        Client user = initializeClient(request);
        Cart cart = initializeCart(user);
        cartRepository.save(cart);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = clientRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public boolean userExists(RegisterRequest request) {
        if(clientRepository.findByEmail(request.getEmail()).isPresent())
            return true;
        return false;
    }

    public Client initializeClient(RegisterRequest request) {
        Client client = new Client();
        client.setEmail(request.getEmail());
        client.setPassword(passwordEncoder.encode(request.getPassword()));
        client.setFirstName(request.getFirstname());
        client.setHasNewsletterOn(request.getHasNewsletterOn());
        return client;
    }

    public Employee initializeEmployee(RegisterRequest request) {
        Employee emp = new Employee();
        emp.setEmail(request.getEmail());
        emp.setPassword(passwordEncoder.encode(request.getPassword()));
        emp.setFirstName(request.getFirstname());
        return emp;
    }

    private Cart initializeCart(Client client) {
        return Cart.builder()
                .client(client)
                .build();
    }
}

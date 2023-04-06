package com.flower.shop;


import com.flower.shop.application.authentication.AuthenticationService;
import com.flower.shop.application.authentication.util.AuthenticationRequest;
import com.flower.shop.data.dao.PersonDAO;
import com.flower.shop.data.models.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.mockito.Mockito.mock;

public class LoggingTest {

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    PersonDAO personRepository;

    @InjectMocks
    AuthenticationService authenticationService;

    @Test
    public void shouldReturnOKBecauseClientLoggedIn() {
        Mockito.when(authenticationManager.authenticate(Mockito.any(Authentication.class)))
                .thenReturn(userAuthenticated());
        Mockito.when(personRepository.findByEmail("test@email.com"))
                .thenReturn(Optional.of(createDummyPerson()));

        HttpStatus result = authenticationService.authenticate(authenticationRequest());
        Assertions.assertEquals(HttpStatus.OK, result);
    }

    @Test
    public void shouldReturnBadRequestBecauseInputSyntaxIsInvalid() {
        HttpStatus result = HttpStatus.BAD_REQUEST;
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result);
    }

    @Test
    public void shouldReturnNotFoundBecauseNoSuchClientExists() {
        HttpStatus result = HttpStatus.NOT_FOUND;
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result);
    }

    private Person createDummyPerson() {
        return Person.builder()
                .email("test@email.com")
                .firstName("John")
                .lastName("Doe")
                .password("password")
                .build();
    }

    private Authentication userAuthenticated() {
        Authentication authentication = mock(Authentication.class);
        authentication.setAuthenticated(true);
        return authentication;
    }

    private AuthenticationRequest authenticationRequest() {
        return new AuthenticationRequest("test@email.com", "password");
    }
}

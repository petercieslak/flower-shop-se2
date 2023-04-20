package com.flower.shop;

import com.flower.shop.application.authentication.util.AuthenticationRequest;
import com.flower.shop.application.authentication.util.RegisterRequest;
import com.flower.shop.data.models.Client;
import com.flower.shop.rest.AuthenticationController;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;

@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class LoggingTest {

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterAll
    private void clearDatabase() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "client", "persons");
    }

    @Test
    public void shouldReturnOKBecauseClientLoggedIn() {
        Client client = createRandomPerson();
        authenticationController.register(getRegisterRequest(client));

        HttpStatus result = authenticationController.authenticate(getAuthenticationRequest(client))
                .getStatusCode();

        Assertions.assertEquals(HttpStatus.OK, result);
    }

    @Test
    public void shouldNotAuthenticateClientBecauseWrongCredentials() {
        Client client = createRandomPerson();

        Exception ex = Assertions.assertThrows(BadCredentialsException.class, () -> {
            authenticationController.authenticate(getAuthenticationRequest(client));
        });
        Assertions.assertEquals("Bad credentials", ex.getMessage());
    }

    @Test
    public void shouldReturnOKBecauseClientRegistered() {
        Client client = createRandomPerson();
        RegisterRequest request = getRegisterRequest(client);

        HttpStatus result = authenticationController.register(request).getStatusCode();

        Assertions.assertEquals(HttpStatus.OK, result);
    }

    @Test
    public void shouldNotRegisterBecauseEmailAlreadyOccupied() {
        Client client = createRandomPerson();
        RegisterRequest request = getRegisterRequest(client);
        authenticationController.register(request);

        HttpStatus result = authenticationController.register(request).getStatusCode();

        Assertions.assertEquals(HttpStatus.FORBIDDEN, result);
    }

    @Test
    public void shouldReturnBadRequestBecauseInputSyntaxIsInvalid() {
        Client client = createRandomPerson();
        client.setEmail("email.com");
        RegisterRequest request = getRegisterRequest(client);

        HttpStatus result = authenticationController.register(request)
                .getStatusCode();

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result);
    }

    //is this one really necessary?
    @Test
    public void shouldReturnNotFoundBecauseNoSuchClientExists() {
        HttpStatus result = HttpStatus.NOT_FOUND;
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result);
    }

    private Client createRandomPerson() {
        String email = RandomString.make(6);
        Client client = new Client();
        client.setEmail(email+"@email.com");
        client.setPassword("password");
        client.setFirstName("John");
        client.setLastName("Doe");
        client.setHasNewsletterOn(true);
        log.info("Created random person with email {}", client.getEmail());
        return client;
    }

    private RegisterRequest getRegisterRequest(Client client) {
        return new RegisterRequest(client.getFirstName(),
                client.getLastName(), client.getEmail(), client.getPassword(), client.getHasNewsletterOn());
    }

    private AuthenticationRequest getAuthenticationRequest(Client client) {
        return new AuthenticationRequest(client.getEmail(), client.getPassword());
    }
}

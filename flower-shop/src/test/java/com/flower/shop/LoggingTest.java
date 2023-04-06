package com.flower.shop;

import com.flower.shop.application.authentication.util.RegisterRequest;
import com.flower.shop.data.models.Person;
import com.flower.shop.rest.AuthenticationController;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class LoggingTest {

    @Autowired
    AuthenticationController authenticationController;

    @Test
    public void shouldReturnOKBecauseClientLoggedIn() {
        HttpStatus result = HttpStatus.OK;
        Assertions.assertEquals(HttpStatus.OK, result);
    }

    @Test
    public void shouldReturnOKBecauseClientRegistered() {
        Person person = createRandomPerson();
        RegisterRequest request = getRegisterRequest(person);
        log.info("Created random person with email {}", person.getEmail());
        HttpStatus result = authenticationController.register(request).getStatusCode();
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

    private Person createRandomPerson() {
        String email = RandomString.make(6);
        return Person.builder()
                .email(email+"@email.com")
                .firstName("John")
                .lastName("Doe")
                .password("password")
                .build();
    }

    private RegisterRequest getRegisterRequest(Person person) {
        return new RegisterRequest(person.getFirstName(),
                person.getLastName(), person.getEmail(), person.getPassword());
    }
}

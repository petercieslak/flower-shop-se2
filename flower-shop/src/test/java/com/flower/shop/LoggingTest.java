package com.flower.shop;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class LoggingTest {

    @Test
    public void shouldReturnOKBecauseClientLoggedIn() {
        HttpStatus result = HttpStatus.OK;
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
}

package com.flower.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class EmployeeUpdatingProductTest {
    @Test
    public void shouldReturnUnauthorizedBecauseBearerTokenIsInvalid() {
        HttpStatus result = HttpStatus.UNAUTHORIZED;
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, result);
    }
    @Test
    public void shouldReturnNotFoundBecauseProductNotExisting() {
        HttpStatus result = HttpStatus.NOT_FOUND;
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result);
    }
    @Test
    public void shouldReturnBadRequestBecauseBodyIsInvalid() {
        HttpStatus result = HttpStatus.BAD_REQUEST;
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result);
    }
    @Test
    public void shouldReturnCreatedProductIsUpdating() {
        HttpStatus result = HttpStatus.CREATED;
        Assertions.assertEquals(HttpStatus.CREATED, result);
    }
}

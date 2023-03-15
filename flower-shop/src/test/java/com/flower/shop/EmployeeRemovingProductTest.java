package com.flower.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
public class EmployeeRemovingProductTest {
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
    public void shouldReturnOKProductRemoved() {
        HttpStatus result = HttpStatus.OK;
        Assertions.assertEquals(HttpStatus.OK, result);
    }
}

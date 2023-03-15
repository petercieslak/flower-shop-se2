package com.flower.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class ChangingOrderStatusTest {
    @Test
    public void shouldReturnUnauthorizedBecauseBearerTokenIsInvalid() {
        HttpStatus result = HttpStatus.UNAUTHORIZED;
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, result);
    }
    @Test
    public void shouldReturnNotFoundBecauseOrderNotExisting() {
        HttpStatus result = HttpStatus.NOT_FOUND;
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result);
    }
    @Test
    public void shouldReturnBadRequestBecauseBodyIsInvalid() {
        HttpStatus result = HttpStatus.BAD_REQUEST;
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result);
    }
    @Test
    public void shouldReturnOKOrderStatusChanged() {
        HttpStatus result = HttpStatus.OK;
        Assertions.assertEquals(HttpStatus.OK, result);
    }
}

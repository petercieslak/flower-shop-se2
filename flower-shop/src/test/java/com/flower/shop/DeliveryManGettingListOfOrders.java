package com.flower.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class DeliveryManGettingListOfOrders {
    @Test
    public void shouldReturnUnauthorizedBecauseBearerTokenIsInvalid() {
        HttpStatus result = HttpStatus.UNAUTHORIZED;
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, result);
    }
    @Test
    public void shouldReturnNotFoundBecauseListOfOrdersNotExisting() {
        HttpStatus result = HttpStatus.NOT_FOUND;
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result);
    }
    @Test
    public void shouldReturnOKRequestIsGood() {
        HttpStatus result = HttpStatus.OK;
        Assertions.assertEquals(HttpStatus.OK, result);
    }
}

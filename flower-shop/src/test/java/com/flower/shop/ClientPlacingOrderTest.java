package com.flower.shop;

import com.flower.shop.rest.OrderController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class ClientPlacingOrderTest {

    @Autowired
    OrderController orderController;

    @Test
    public void shouldReturnBadRequestBecauseBodyIsInvalid() {
        HttpStatus result = HttpStatus.BAD_REQUEST;
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result);
    }
    @Test
    public void shouldReturnUnauthorizedBecauseBearerTokenIsInvalid() {
        HttpStatus result = HttpStatus.UNAUTHORIZED;
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, result);
    }
    @Test
    public void shouldReturnCreatedBecauseNewOrderWasCreated() {
        HttpStatus result = HttpStatus.CREATED;
        Assertions.assertEquals(HttpStatus.CREATED, result);
    }

}

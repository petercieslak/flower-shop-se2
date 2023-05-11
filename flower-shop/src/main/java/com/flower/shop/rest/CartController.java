package com.flower.shop.rest;

import com.flower.shop.application.domain.services.CartProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/cart")
public class CartController {
    @Autowired
    CartProductsService cartProductsService;

    @CrossOrigin
    @GetMapping()
    public ResponseEntity<List<String>> getItems(String string) {
        UUID uuid = UUID.fromString(string);
        return ResponseEntity.ok(cartProductsService.findAllItems(uuid));
    }

    @CrossOrigin
    @DeleteMapping
    public int removeFromCart(String user, String product) {
        UUID uuid = UUID.fromString(user);
//        UUID uuid_product = UUID.fromString(product);
        return cartProductsService.deleteProductFromCart(uuid, product);
    }

}
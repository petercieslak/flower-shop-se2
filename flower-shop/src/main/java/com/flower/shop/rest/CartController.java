package com.flower.shop.rest;

import com.flower.shop.application.domain.services.CartProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public int removeFromCart(String cart_id, String product) {
        UUID uuid = UUID.fromString(cart_id);
        return cartProductsService.deleteProductFromCart(uuid, product);
    }

    @CrossOrigin
    @PostMapping
    public int insertIntoCartProducts(String cart_id, String product_id){
        UUID uuid = UUID.fromString(cart_id);
        return cartProductsService.insertIntoCartProducts(uuid, product_id);
    }

}

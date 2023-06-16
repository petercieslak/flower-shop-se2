package com.flower.shop.rest;

import com.flower.shop.application.domain.services.CartProductsService;
import com.flower.shop.data.models.Cart;
import com.flower.shop.data.models.CartProducts;
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
    public ResponseEntity<List<CartProducts>> getProducts(@RequestParam UUID userId) {
        return ResponseEntity.ok(cartProductsService.getProducts(userId));
    }

    @CrossOrigin
    @PutMapping
    public ResponseEntity<Cart> addProduct(@RequestParam UUID userId, @RequestParam UUID productId, Integer quantity) {
        return ResponseEntity.ok(cartProductsService.addProduct(userId, productId, quantity));
    }

    @CrossOrigin
    @DeleteMapping
    public ResponseEntity<Cart> removeProduct(@RequestParam UUID userId, @RequestParam UUID productId){
        return ResponseEntity.ok(cartProductsService.removeProduct(userId, productId));
    }

}

package com.flower.shop.rest;

import com.flower.shop.application.domain.services.CartProductsService;
import com.flower.shop.application.dto.CartProductsDto;
import com.flower.shop.data.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Cart>> getItems() {
//        UUID uuid = UUID.fromString(string);
        return ResponseEntity.ok(cartProductsService.findAll());
    }
}

package com.flower.shop.rest;

import com.flower.shop.application.domain.services.CartService;
import com.flower.shop.application.dto.CartDto;
import com.flower.shop.data.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @CrossOrigin
    @GetMapping()
    public ResponseEntity<List<CartDto>> findClientCart(String mail) {
        List<Cart> c = cartService.findClientCart(mail);
        List<CartDto> list = new ArrayList<CartDto>();
        for (Cart cart : c) {
            list.add(new CartDto(cart.getCartPKId().getProductId(), cart.getQuantity()));
        }
        return ResponseEntity.ok(list);
    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<Void> insertCartProducts(String mail, String product_name, Integer quantity){
        return cartService.insertCartProducts(mail, product_name, quantity);
    }
    @CrossOrigin
    @PutMapping
    public ResponseEntity<Void> changeCartProducts(String mail, String product_name, Integer quantity){
        return cartService.changeCartProducts(mail, product_name, quantity);
    }

    @CrossOrigin
    @DeleteMapping
    public ResponseEntity<Void> deleteCartProducts(String mail, String product_name){
        return cartService.deleteCartProducts(mail, product_name);
    }
}

package com.flower.shop.rest;

import com.flower.shop.application.domain.services.CartService;
import com.flower.shop.data.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @CrossOrigin
    @GetMapping()
    public Map<UUID, Integer> findClientCart(String mail) {
        List<Cart> c = cartService.findClientCart(mail);
        Map<UUID, Integer> map = new HashMap<>();
        for (Cart cart : c) {
            map.put(cart.getCartPKId().getProductId(),
                    cart.getQuantity());
        }
        return map;
    }
    @CrossOrigin
    @PostMapping
    public int changeCartProducts(String mail, String product_name, Integer quantity){
        return cartService.insertIntoCartProducts(mail, product_name, quantity);
    }
}

package com.flower.shop.application.domain.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;
import com.flower.shop.data.dao.CartProductsDAO;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class CartProductsService {

    @Autowired
    private CartProductsDAO cartProductsDAO;
    public List<String> findAllItems(UUID uuid) {
        return cartProductsDAO.findAllItems(uuid);
    }

    public int deleteProductFromCart(UUID cart_id, String product_id){
        return cartProductsDAO.deleteProductFromCart(cart_id, product_id);
    };

    public int insertIntoCartProducts(UUID cart_id, String product_id){
        return cartProductsDAO.insertIntoCartProducts(cart_id, product_id);
    }
}

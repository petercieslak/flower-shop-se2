package com.flower.shop.data.dao;

import com.flower.shop.application.dto.CartProductsDto;
import com.flower.shop.data.models.Cart;
import com.flower.shop.data.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
@Repository
public interface CartProductsDAO extends JpaRepository<Cart, UUID> {
    @Query(value = "select products_product_id from cart_products cp where cp.cart_id = ?1", nativeQuery = true)
    List<String> findAllItems(UUID uuid);

    @Modifying
    @Transactional
    @Query(value = "delete from cart_products cp where cp.cart_id = ?1 and cp.products_product_id = ?2", nativeQuery = true)
    int deleteProductFromCart(UUID cart_id, String product_id);
}
package com.flower.shop.data.dao;

import com.flower.shop.data.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface CartDAO extends JpaRepository<Cart, UUID> {

    @Query(value = "select * from cart c where c.client_id = ?1", nativeQuery = true)
    List<Cart> findUserCart(UUID cart_id);

    @Query(value = "select c.quantity from cart c where c.client_id = ?1 and c.product_id = ?2", nativeQuery = true)
    int checkQuantity(UUID cart_id, UUID product_id);

    @Query(value = "select count(1) from cart c where c.client_id = ?1 and c.product_id = ?2", nativeQuery = true)
    int checkIfExists(UUID cart_id, UUID product_id);

    @Modifying
    @Transactional
    @Query(value = "update cart c set quantity = ?3 where c.client_id = ?1 and c.product_id =?2", nativeQuery = true)
    void changeQuantityInCart(UUID cart_id, UUID product_id, int new_quantity);

    @Modifying
    @Transactional
    @Query(value = "delete from cart c where c.client_id = ?1 and c.product_id = ?2", nativeQuery = true)
    int deleteProductFromCart(UUID cart_id, UUID product_id);
}

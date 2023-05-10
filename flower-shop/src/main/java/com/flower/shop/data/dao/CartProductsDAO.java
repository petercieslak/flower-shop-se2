package com.flower.shop.data.dao;

import com.flower.shop.application.dto.CartProductsDto;
import com.flower.shop.data.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CartProductsDAO extends JpaRepository<Cart, UUID> {
}

package com.flower.shop.data.dao;

import com.flower.shop.data.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ProductDAO extends JpaRepository<Product, UUID> {
}

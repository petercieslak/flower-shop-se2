package com.flower.shop.data.dao;

import com.flower.shop.data.models.Person;
import com.flower.shop.data.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductDAO extends JpaRepository<Product, UUID> {
    Page<Product> findByFlowerType(String type, Pageable pageable);

    Optional<Product> findByName(String name);

//    @Query(value = "select product_id from product p where p.name = ?1", nativeQuery = true)
//    UUID findFlowerUUIDByName(String name);
}

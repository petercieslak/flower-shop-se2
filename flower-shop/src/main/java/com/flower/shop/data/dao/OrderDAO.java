package com.flower.shop.data.dao;

import com.flower.shop.data.models.Order;
import com.flower.shop.data.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

@Repository
public interface OrderDAO extends JpaRepository<Order, UUID> {
    Page<Order> findByClient(Optional<Person> client, Pageable pageable);
}

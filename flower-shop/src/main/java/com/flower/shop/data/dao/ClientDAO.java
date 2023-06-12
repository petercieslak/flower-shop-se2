package com.flower.shop.data.dao;

import com.flower.shop.data.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientDAO extends JpaRepository<Person, UUID> {
    Optional<Person> findByEmail(String email);
}

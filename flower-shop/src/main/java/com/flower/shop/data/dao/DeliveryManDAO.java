package com.flower.shop.data.dao;

import com.flower.shop.data.models.DeliveryMan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DeliveryManDAO extends JpaRepository<DeliveryMan, UUID> {
    public List<DeliveryMan> findAllByDeliveryCity(String city);
}

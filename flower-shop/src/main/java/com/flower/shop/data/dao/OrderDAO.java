package com.flower.shop.data.dao;

import com.flower.shop.data.models.DeliveryMan;
import com.flower.shop.data.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderDAO extends JpaRepository<Order, UUID> {
    public int countAllByDeliveryMan(DeliveryMan deliveryMan);
}

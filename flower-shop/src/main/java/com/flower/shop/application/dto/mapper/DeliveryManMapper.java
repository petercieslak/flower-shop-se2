package com.flower.shop.application.dto.mapper;

import com.flower.shop.application.dto.DeliveryManDto;
import com.flower.shop.data.models.DeliveryMan;

public class DeliveryManMapper {

    public DeliveryMan toEntity(DeliveryManDto deliveryManDto) {
        DeliveryMan deliveryMan = new DeliveryMan();
        deliveryMan.setFirstName(deliveryManDto.getFirstName());
        deliveryMan.setLastName(deliveryManDto.getLastName());
        deliveryMan.setEmail(deliveryManDto.getEmail());
        deliveryMan.setPassword(deliveryManDto.getPassword());
        deliveryMan.setDeliveryCity(deliveryManDto.getDeliveryCity().toUpperCase());
        return  deliveryMan;
    }
}

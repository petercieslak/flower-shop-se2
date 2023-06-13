package com.flower.shop.application.domain.services;

import com.flower.shop.application.dto.DeliveryManDto;
import com.flower.shop.application.dto.mapper.DeliveryManMapper;
import com.flower.shop.data.dao.DeliveryManDAO;
import com.flower.shop.data.models.DeliveryMan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeliveryManService {

    @Autowired
    private DeliveryManDAO deliveryManRepository;

    private DeliveryManMapper deliveryManMapper = new DeliveryManMapper();

    public DeliveryMan createDeliveryMan(DeliveryManDto deliveryManDto) {
        DeliveryMan deliveryMan = deliveryManMapper.toEntity(deliveryManDto);
        log.info("Adding delivery man: " + deliveryMan);
        return deliveryManRepository.save(deliveryMan);
    }
}

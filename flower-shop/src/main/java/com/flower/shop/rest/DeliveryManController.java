package com.flower.shop.rest;

import com.flower.shop.application.domain.services.DeliveryManService;
import com.flower.shop.application.dto.DeliveryManDto;
import com.flower.shop.data.models.DeliveryMan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/delivery")
@Slf4j
public class DeliveryManController {

    @Autowired
    private DeliveryManService deliveryManService;

    @PostMapping
    public ResponseEntity<DeliveryMan> createDeliveryMan(DeliveryManDto deliveryMan) {
        if(deliveryMan != null)
            return ResponseEntity.ok(deliveryManService.createDeliveryMan(deliveryMan));
        else
            return ResponseEntity.badRequest().build();
    }
}

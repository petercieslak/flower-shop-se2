package com.flower.shop.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartProductsDto {
    private UUID cart_id;
    private UUID products_product_id;

//    public CartProductsDto(UUID uuid, UUID uuid1) {
//        cart_id = uuid;
//        products_product_id = uuid1;
//    }
}
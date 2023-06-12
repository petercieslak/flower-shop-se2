package com.flower.shop.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartDto {
    private UUID cart_id;
    private UUID product_id;
    private int quantity;
}

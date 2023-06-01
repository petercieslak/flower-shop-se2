package com.flower.shop.application.dto;

import com.flower.shop.data.models.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
    private UUID productId;

    private String name;

    private String description;

    private byte[] image;

    private double price;
    private String flowerType;
}

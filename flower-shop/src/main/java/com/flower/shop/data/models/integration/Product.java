package com.flower.shop.data.models.integration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private UUID productID;

    private String name;

    private String description;

    private String image;

    private double price;

    private int quantity;

    private String category;

}

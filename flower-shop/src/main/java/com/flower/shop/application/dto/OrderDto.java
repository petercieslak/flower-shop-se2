package com.flower.shop.application.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private String status;

    private AddressDto deliveryAddress;

    private UUID clientId;

    private List<ProductDto> products;

}

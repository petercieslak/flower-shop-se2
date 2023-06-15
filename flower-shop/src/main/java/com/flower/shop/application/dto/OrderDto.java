package com.flower.shop.application.dto;

import com.flower.shop.data.models.OrderProducts;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private UUID orderId;
    private String status;

    private AddressDto deliveryAddress;

    private UUID clientId;

    private List<OrderProducts> products;

}

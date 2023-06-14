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
public class ModifyOrderDto {

    private AddressDto deliveryAddress;

    private List<OrderProducts> products;

}

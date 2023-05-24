package com.flower.shop.application.dto;

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

    private List<UUID> products;

}

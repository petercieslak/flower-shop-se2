package com.flower.shop.application.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModifyOrderStatusDto {
    private String orderStatus;
}

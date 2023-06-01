package com.flower.shop.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {

    private String street;

    private String city;

    private String postalCode;

    private String country;
}

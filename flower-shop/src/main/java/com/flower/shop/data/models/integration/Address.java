package com.flower.shop.data.models.integration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    private String street;

    private String buildingNo;

    private String houseNo;

    private String postalCode;

    private String city;

    private String country;
}

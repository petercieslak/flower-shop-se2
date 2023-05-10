package com.flower.shop.application.dto.mapper;

import com.flower.shop.application.dto.AddressDto;
import com.flower.shop.data.models.Address;

public class AddressMapper {

    public AddressDto toDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setCity(address.getCity());
        addressDto.setCountry(address.getCountry());
        addressDto.setStreet(address.getStreet());
        addressDto.setPostalCode(address.getPostalCode());
        return addressDto;
    }
}

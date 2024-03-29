package com.flower.shop.application.dto.mapper;

import com.flower.shop.application.dto.OrderDto;
import com.flower.shop.data.models.Order;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    ProductMapper productMapper = new ProductMapper();

    AddressMapper addressMapper = new AddressMapper();

    public OrderDto toDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setProducts(order.getProducts());
        orderDto.setStatus(order.getStatus());
        orderDto.setDeliveryAddress(addressMapper
                .toDto(order.getDeliveryAddress()));
        orderDto.setClientId(order.getClient().getId());
        orderDto.setOrderId(order.getId());
        return orderDto;
    }
}

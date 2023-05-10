package com.flower.shop.application.domain.services;

import com.flower.shop.application.dto.AddressDto;
import com.flower.shop.application.dto.OrderDto;
import com.flower.shop.application.dto.mapper.OrderMapper;
import com.flower.shop.application.dto.util.OrderStatus;
import com.flower.shop.data.dao.CartDAO;
import com.flower.shop.data.dao.ClientDAO;
import com.flower.shop.data.dao.OrderDAO;
import com.flower.shop.data.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class OrderService {

    @Autowired
    private CartDAO cartRepository;

    @Autowired
    private OrderDAO orderRepository;

    @Autowired
    private OrderStatus orderStatus;

    private OrderMapper orderMapper = new OrderMapper();

    public OrderDto createOrder(UUID clientId, AddressDto address) {
        Optional<Cart> cart = cartRepository.findById(clientId);
        Order order = initOrder(cart, address);
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    private Order initOrder(Optional<Cart> cart, AddressDto address) {
        Order order = new Order();
        order.setClient(cart.get().getClient());
        order.setStatus(orderStatus.ACTIVE);
        order.setProducts(initOrderProducts(cart.get().getProducts()));
        order.setDeliveryAddress(initAddress(address));
        return order;
    }

    private List<Product> initOrderProducts(List<Product> products) {
        List<Product> orderProducts = new ArrayList<>();
        for(Product product : products) {
            orderProducts.add(product);
        }
        return orderProducts;
    }

    private Address initAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setCountry(addressDto.getCountry());
        address.setPostalCode(addressDto.getPostalCode());
        return address;
    }
}

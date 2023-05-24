package com.flower.shop.application.domain.services;

import com.flower.shop.application.dto.*;
import com.flower.shop.application.dto.mapper.OrderMapper;
import com.flower.shop.application.dto.util.OrderStatus;
import com.flower.shop.data.dao.CartDAO;
import com.flower.shop.data.dao.ClientDAO;
import com.flower.shop.data.dao.OrderDAO;
import com.flower.shop.data.dao.ProductDAO;
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
    private ProductDAO productRepository;

    @Autowired
    private OrderStatus orderStatus;

    private OrderMapper orderMapper = new OrderMapper();

    public OrderDto createOrder(UUID clientId, AddressDto address) {
        Optional<Cart> cart = cartRepository.findById(clientId);
        Order order = initOrder(cart, address);
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }
    public void modifyOrder(UUID orderId, ModifyOrderDto orderdto) {
        Order modifiedOrder = findOrder(orderId).get();
        modifiedOrder.setDeliveryAddress(initAddress(orderdto.getDeliveryAddress()));
        modifiedOrder.setProducts(initProducts(orderdto.getProducts()));
        orderRepository.save(modifiedOrder);

    }
    public Optional<Order> findOrder(UUID orderId){
        return orderRepository.findById(orderId);
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

    private List<Product> initProducts(List<UUID> _products) {
        List<Product> products = new ArrayList<>();
        for (UUID prodId : _products) {
            Optional<Product> newProd = productRepository.findById(prodId);
            products.add(newProd.get());
        }
        return products;
    }
}

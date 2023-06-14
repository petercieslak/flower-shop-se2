package com.flower.shop.application.domain.services;

import com.flower.shop.application.dto.*;
import com.flower.shop.application.dto.mapper.OrderMapper;
import com.flower.shop.application.dto.util.OrderStatus;
import com.flower.shop.data.dao.*;
import com.flower.shop.data.models.*;
import com.flower.shop.data.models.keys.OrderProductsId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Component
public class OrderService {

    @Autowired
    private CartDAO cartRepository;

    @Autowired
    private OrderDAO orderRepository;

    @Autowired
    private ClientDAO clientRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductDAO productRepository;

    @Autowired
    private DeliveryManDAO deliveryManRepository;

    @Autowired
    private OrderStatus orderStatus;

    public List<OrderDto> getOrders(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Order> orders = orderRepository.findAll(pageable);
        List<Order> listOfOrders = orders.getContent();
        List<OrderDto> result = listOfOrders.stream().
                map(p -> orderMapper.toDto(p)).
                collect(Collectors.toList());
        return result;
    }

    public List<OrderDto> getClientOrders(int pageNo, int pageSize, UUID clientId) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Order> orders = orderRepository.findByClient(clientRepository.findById(clientId), pageable);
        List<Order> listOfOrders = orders.getContent();
        List<OrderDto> result= listOfOrders.stream().
                map(p -> orderMapper.toDto(p)).
                collect(Collectors.toList());
        return result;
    }

    public OrderDto createOrder(UUID clientId, AddressDto address) {
        Optional<Cart> cart = cartRepository.findById(clientId);
        Order order = initOrder(cart, address);
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    public void modifyOrder(UUID orderId, ModifyOrderDto orderdto) {
        Order modifiedOrder = findOrder(orderId).get();
        modifiedOrder.setDeliveryAddress(initAddress(orderdto.getDeliveryAddress()));
        modifiedOrder.setProducts(orderdto.getProducts());
        orderRepository.save(modifiedOrder);

    }

    public void modifyOrderStatus(UUID orderId, ModifyOrderStatusDto orderdto) {
        Order modifiedOrder = findOrder(orderId).get();
        modifiedOrder.setStatus(orderdto.getOrderStatus());
        orderRepository.save(modifiedOrder);
    }

    public Optional<Order> findOrder(UUID orderId){
        return orderRepository.findById(orderId);
    }

    private Order initOrder(Optional<Cart> cart, AddressDto address) {
        Order order = new Order();
        order.setClient(cart.get().getClient());
        order.setStatus(orderStatus.ACTIVE);
        order.setProducts(initOrderProducts(cart.get().getProducts(), order));
        order.setDeliveryAddress(initAddress(address));
        order.setDeliveryMan(getDeliveryMan(address.getCity()));
        return order;
    }

    private DeliveryMan getDeliveryMan(String city) {
        List<DeliveryMan> availableDeliveryMen = deliveryManRepository.findAllByDeliveryCity(city);
        return selectDeliveryMan(availableDeliveryMen);
    }

    //fixme index out of bounds if no delivery man is available
    private DeliveryMan selectDeliveryMan(List<DeliveryMan> availableDeliveryMen) {
        return availableDeliveryMen.stream()
                .sorted(Comparator.comparing(d -> orderRepository.countAllByDeliveryMan(d)))
                .collect(Collectors.toList())
                .get(0);
    }

    private List<OrderProducts> initOrderProducts(List<CartProducts> products, Order order) {
        List<OrderProducts> orderProducts = new ArrayList<>();
        for(CartProducts product : products) {
            orderProducts.add(mapToOrderProducts(product, order));
        }
        return orderProducts;
    }

    private Address initAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setCity(addressDto.getCity().toUpperCase());
        address.setStreet(addressDto.getStreet().toUpperCase());
        address.setCountry(addressDto.getCountry().toUpperCase());
        address.setPostalCode(addressDto.getPostalCode());
        return address;
    }

    private OrderProducts mapToOrderProducts(CartProducts cartProducts, Order order) {
        OrderProducts orderProduct = new OrderProducts();
        OrderProductsId orderProductsId = new OrderProductsId(order.getId(), cartProducts.getProduct().getProductId());
        orderProduct.setOrderProductsId(orderProductsId);
        orderProduct.setProduct(cartProducts.getProduct());
        orderProduct.setOrders(order);
        orderProduct.setQuantity(cartProducts.getQuantity());
        return orderProduct;
    }
}

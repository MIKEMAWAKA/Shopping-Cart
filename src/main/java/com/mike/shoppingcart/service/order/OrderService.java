package com.mike.shoppingcart.service.order;

import com.mike.shoppingcart.model.Order;

import java.util.List;

public interface OrderService {

    Order placeOrder(Long userId);


    Order getOrder(Long orderId);


    List<Order> getUserOrders(Long userId);
}

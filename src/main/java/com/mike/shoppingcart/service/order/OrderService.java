package com.mike.shoppingcart.service.order;

import com.mike.shoppingcart.dto.OrderDto;
import com.mike.shoppingcart.model.Order;

import java.util.List;

public interface OrderService {

    Order placeOrder(Long userId);


    OrderDto getOrder(Long orderId);


    List<OrderDto> getUserOrders(Long userId);
}

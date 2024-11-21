package com.mike.shoppingcart.service.order;

import com.mike.shoppingcart.model.Order;

public interface OrderService {

    Order placeOrder(Order order);


    Order getOrder(Long orderId);



}

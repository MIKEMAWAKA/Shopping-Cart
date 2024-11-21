package com.mike.shoppingcart.reposistory;

import com.mike.shoppingcart.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository  extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);
}

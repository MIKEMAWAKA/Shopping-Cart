package com.mike.shoppingcart.reposistory;

import com.mike.shoppingcart.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order, Long> {

}

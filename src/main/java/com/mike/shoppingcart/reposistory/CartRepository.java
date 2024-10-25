package com.mike.shoppingcart.reposistory;

import com.mike.shoppingcart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository  extends JpaRepository<Cart,Long> {

}

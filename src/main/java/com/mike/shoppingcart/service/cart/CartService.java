package com.mike.shoppingcart.service.cart;

import com.mike.shoppingcart.model.Cart;

import java.math.BigDecimal;

public interface CartService {

    Cart getCart(Long id);

    void clearCart(Long id);

    BigDecimal getTotalPrice();

}

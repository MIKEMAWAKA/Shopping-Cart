package com.mike.shoppingcart.service.cart;


import com.mike.shoppingcart.exceptions.ResourceNotFoundException;
import com.mike.shoppingcart.model.Cart;
import com.mike.shoppingcart.reposistory.CartItemRepository;
import com.mike.shoppingcart.reposistory.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;


    @Override
    public Cart getCart(Long id) {
      Cart cart= cartRepository.findById(id)
                .orElseThrow(
                        () ->new ResourceNotFoundException("Cart Not Found")
                );
      BigDecimal total = cart.getTotalAmount();
      cart.setTotalAmount(total);
      return cart;
    }

    @Override
    public void clearCart(Long id) {
        Cart cart = getCart(id);
        cartItemRepository.deleteAllByCartId(id);
        cartRepository.deleteById(id);


    }

    @Override
    public BigDecimal getTotalPrice() {
        return null;
    }
}

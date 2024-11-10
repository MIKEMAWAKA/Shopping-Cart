package com.mike.shoppingcart.service.cart;


import com.mike.shoppingcart.exceptions.ResourceNotFoundException;
import com.mike.shoppingcart.model.Cart;
import com.mike.shoppingcart.reposistory.CartItemRepository;
import com.mike.shoppingcart.reposistory.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;




    private final AtomicLong cartIdGenerator = new AtomicLong(0);


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

    @Transactional
    @Override
    public void clearCart(Long id) {
        Cart cart = getCart(id);
        cartItemRepository.deleteAllByCartId(id);
        cart.getItems().clear();
        cartRepository.deleteById(id);

    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        Cart cart = getCart(id);
        return cart.getTotalAmount();
    }



    @Override
    public Long initializeNewCart() {
        Cart newCart = new Cart();
        Long newCartId = cartIdGenerator.incrementAndGet();
        newCart.setId(newCartId);
        return cartRepository.save(newCart).getId();

    }

    @Override
    public Cart getCartByUserId(Long userId) {
//        return cartRepository.findByUserId(userId);
        return null;
    }
}

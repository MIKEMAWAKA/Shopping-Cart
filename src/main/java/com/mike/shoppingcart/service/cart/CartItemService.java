package com.mike.shoppingcart.service.cart;

import com.mike.shoppingcart.model.CartItem;

public interface CartItemService {

    void  addItemToCart(Long cartId, Long productId, int quantity);

    void  remoteItemFromCart(Long cartId, Long productId);

    void  updateItemToCart(Long cartId, Long productId, int quantity);




    CartItem getCartItem(Long cartId, Long productId);
}

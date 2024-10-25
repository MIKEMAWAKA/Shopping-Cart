package com.mike.shoppingcart.service.cart;

public interface CartItemService {

    void  addItemToCart(Long cartId, long productId, int quantity);

    void  remoteItemFromCart(Long cartId, Long productId);

    void  updateItemToCart(Long cartId, long productId, int quantity);


}

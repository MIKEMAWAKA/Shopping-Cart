package com.mike.shoppingcart.service.cart;

import com.mike.shoppingcart.model.Cart;
import com.mike.shoppingcart.model.CartItem;
import com.mike.shoppingcart.model.Product;
import com.mike.shoppingcart.reposistory.CartItemRepository;
import com.mike.shoppingcart.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

public class CartItemServiceImp implements CartItemService{

    @Autowired
     private CartItemRepository cartItemRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private  CartService cartService;


    @Override
    public void addItemToCart(Long cartId, long productId, int quantity) {
        Cart cart = cartService.getCart(cartId);

        Product product = productService.getProductById(productId);

        CartItem cartItem = cart.getCartItems()
                .stream().filter(item ->item.
                        getProduct().equals(product)).
                       findFirst().orElse(null);

        if (cartItem.getId()==null){
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(product.getPrice());
            cartItem.setCart(cart);
        } else {
            cartItem.setQuantity(cartItem.getQuantity()+quantity);



        }









    }

    @Override
    public void remoteItemFromCart(Long cartId, Long productId) {

    }

    @Override
    public void updateItemToCart(Long cartId, long productId, int quantity) {

    }
}

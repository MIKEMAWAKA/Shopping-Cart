package com.mike.shoppingcart.controller;


import com.mike.shoppingcart.exceptions.ResourceNotFoundException;

import com.mike.shoppingcart.response.ApiResponse;
import com.mike.shoppingcart.service.cart.CartItemService;

import com.mike.shoppingcart.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.IllegalFormatCodePointException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;


    @Autowired
    private CartService cartService;


    @PostMapping("/items/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam Long cartId,
                                                     @RequestParam Long productId,
                                                     @RequestParam Integer quantity){

        try {

            if (cartId==null){
               cartId= cartService.initializeNewCart();
            }

            cartItemService.addItemToCart(cartId,productId,quantity);
            return  ResponseEntity.ok(new ApiResponse("Add Item Success",null));
        }catch (ResourceNotFoundException e){
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }




    }


    @DeleteMapping("/cart/{cartId}/{productId}")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId,@PathVariable Long productId){
        try {
            cartItemService.remoteItemFromCart(cartId,productId);
            return  ResponseEntity.ok(new ApiResponse("Success",null));
        }catch (ResourceNotFoundException e){
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }


    }


    @PutMapping("/cart/{cartId}/item/{itemId}")
    public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId,@PathVariable Long itemId,
                                                          @RequestParam Integer quantity){
        try {
        cartItemService.updateItemToCart(cartId,itemId,quantity);
            return  ResponseEntity.ok(new ApiResponse("Success",null));
        }catch (ResourceNotFoundException e){
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }


    }


}

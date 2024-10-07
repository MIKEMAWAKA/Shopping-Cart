package com.mike.shoppingcart.exceptions;

public class ProductNotFoundException extends  RuntimeException{

    public  ProductNotFoundException(String message){
        super(message);
    }
}

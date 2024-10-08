package com.mike.shoppingcart.request;


import com.mike.shoppingcart.model.Category;
import lombok.Data;


import java.math.BigDecimal;


@Data
public class AddProductRequest {

    private  String name;
    private  String brand;

    private BigDecimal price;
    private  int inventory;

    private  String description;


    private Category category;



}

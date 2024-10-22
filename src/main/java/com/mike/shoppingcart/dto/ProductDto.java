package com.mike.shoppingcart.dto;

import com.mike.shoppingcart.model.Category;
import com.mike.shoppingcart.model.Image;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data

public class ProductDto {



    private  Long id;
    private  String name;
    private  String brand;

    private BigDecimal price;
    private  int inventory;

    private  String description;


    private String productCode;


    private Category category;


    private List<ImageDto> images;


}

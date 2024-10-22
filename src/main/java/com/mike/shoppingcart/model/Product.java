package com.mike.shoppingcart.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String name;
    private  String brand;

    private BigDecimal price;
    private  int inventory;

    private  String description;

    @Column(unique = true, length = 10)
    private String productCode;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private  Category category;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Image> images;


    public Product(String name, String brand, BigDecimal price, int inventory, String description, Category category) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.inventory = inventory;
        this.description = description;
        this.category = category;
        this.productCode = generateUniqueProductCode();
    }

//    public Product() {
//
//    }

    // Method to generate a product code starting with "P" followed by 9 digits
    private String generateUniqueProductCode() {
        Random random = new Random();
        return "P" + String.format("%09d", random.nextInt(1_000_000_000));
    }
}

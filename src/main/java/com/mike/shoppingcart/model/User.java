package com.mike.shoppingcart.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private  String firstname;
    private  String lastname;

    @NaturalId
    private  String email;
    private  String password;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private  Cart cart;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> order;



}

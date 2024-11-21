package com.mike.shoppingcart.dto;

import lombok.Data;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<OrderDto> orders;
    private CartDto cart;
}
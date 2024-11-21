package com.mike.shoppingcart.request;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateUserReq {


    private  String firstname;
    private  String lastname;
    private  String email;
    private  String password;
}

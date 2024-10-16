package com.mike.shoppingcart.controller;


import com.mike.shoppingcart.model.Category;
import com.mike.shoppingcart.model.Product;
import com.mike.shoppingcart.response.ApiResponse;
import com.mike.shoppingcart.service.category.CategoryService;
import com.mike.shoppingcart.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController
{
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts(){

        try {
            List<Product> products = productService.getAllProducts();

            return ResponseEntity.ok(new ApiResponse("Found",products));


        } catch (Exception e){
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error: ",INTERNAL_SERVER_ERROR));
        }



    }
}

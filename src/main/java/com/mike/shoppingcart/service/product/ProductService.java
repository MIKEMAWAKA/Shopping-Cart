package com.mike.shoppingcart.service.product;

import com.mike.shoppingcart.model.Product;
import com.mike.shoppingcart.request.AddProductRequest;

import java.util.List;

public interface ProductService {
    Product addProduct(AddProductRequest request);





    Product getProductById(Long id);


    void deleteProductById(Long id);


    Product updateProduct(AddProductRequest product,Long productId);

    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);

    List<Product> getProductBsyBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category,String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand,String name);

   Long countProductsByBrandAndName(String brand,String name);

}

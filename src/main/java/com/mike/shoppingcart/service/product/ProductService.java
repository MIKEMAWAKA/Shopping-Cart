package com.mike.shoppingcart.service.product;

import com.mike.shoppingcart.model.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);





    Product getProductById(Long id);


    void deleteProductById(Long id);


    void updateProduct(Product product,Long productId);

    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);

    List<Product> getProductBsyBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category,String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand,String name);

   Long countProductsByBrandAndName(String brand,String name);

}

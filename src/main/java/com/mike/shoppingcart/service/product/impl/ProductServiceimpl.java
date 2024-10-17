package com.mike.shoppingcart.service.product.impl;

import com.mike.shoppingcart.exceptions.ProductNotFoundException;
import com.mike.shoppingcart.model.Category;
import com.mike.shoppingcart.model.Product;
import com.mike.shoppingcart.reposistory.CategoryRepository;
import com.mike.shoppingcart.reposistory.ProductRepository;
import com.mike.shoppingcart.request.AddProductRequest;
import com.mike.shoppingcart.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceimpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Product addProduct(AddProductRequest request) {

        //checkif catefory if found in db is yes st it as new product if no save it
        //set if exist
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(()->{
                    Category newCategory = new Category(request.getCategory().getName());
                    return  categoryRepository.save(newCategory);
                });
        request.setCategory(category);
        return productRepository.save(createProduct(request,category));
    }


    private  Product createProduct(AddProductRequest request, Category category){
        return  new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }




    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product not found!"));
    }

    @Override
    public void deleteProductById(Long id) {

    productRepository.findById(id).ifPresentOrElse(productRepository::delete,
            ()->{throw new ProductNotFoundException("Product not found!");});



    }

    @Override
    public Product updateProduct(AddProductRequest request, Long productId) {

        return productRepository.findById(productId)
                .map(existingProduct ->updateProductreq(existingProduct,request) )
                .map(productRepository::save)
                .orElseThrow(()->new ProductNotFoundException("Product not found"));






//        productRepository.findById(productId).ifPresentOrElse(productRepository.save(product),
//                ()->{throw new ProductNotFoundException("Product not found!");});
    }


    private  Product updateProductreq(Product existingProduct,AddProductRequest request){
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
//
//        return  new Product(
//                request.getName(),
//                request.getBrand(),
//                request.getPrice(),
//                request.getInventory(),
//                request.getDescription(),
//                category
//        );

        return  existingProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductBsyBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category,brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName( String brand,String name) {

        return productRepository.findByBrandAndName(brand,name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand,name);
    }
}

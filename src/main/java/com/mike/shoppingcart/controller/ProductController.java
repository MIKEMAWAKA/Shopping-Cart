package com.mike.shoppingcart.controller;


import com.mike.shoppingcart.dto.ProductDto;
import com.mike.shoppingcart.exceptions.ResourceNotFoundException;
import com.mike.shoppingcart.model.Product;
import com.mike.shoppingcart.request.AddProductRequest;
import com.mike.shoppingcart.response.ApiResponse;
import com.mike.shoppingcart.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

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

            List<ProductDto> convertedProducts = productService.getConvertedProduct(products);

            return ResponseEntity.ok(new ApiResponse("Found",convertedProducts));


        } catch (Exception e){
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error: ",e.getMessage()));
        }



    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCProduct(@RequestBody AddProductRequest request){

        try {
            Product product1  = productService.addProduct(request);

            return ResponseEntity.ok(new ApiResponse("Successfully added",product1));


        } catch (Exception e){
            return  ResponseEntity.status(CONFLICT)
                    .body(new ApiResponse(e.getMessage(),null));
        }


    }


    @PutMapping("/product/{id}/update")
    public  ResponseEntity<ApiResponse> productUpdate(@PathVariable  Long id, @RequestBody AddProductRequest request){


        try {
            Product product = productService.updateProduct(request,id);

            return  ResponseEntity.ok(new ApiResponse("Successfully",product));

        } catch (Exception e){
            return  ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }


    @GetMapping("/product/{id}/product")
    public  ResponseEntity<ApiResponse> getProductById(@PathVariable  Long id){


        try {
            Product product = productService.getProductById(id);

           var convertedProducts = productService.convertoDto(product);

            return  ResponseEntity.ok(new ApiResponse("Successfully",convertedProducts));

        } catch (ResourceNotFoundException e){
            return  ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }


    @DeleteMapping("/product/{id}/delete")
    public  ResponseEntity<ApiResponse> deleteProduct(@PathVariable  Long id){


        try {
            productService.deleteProductById(id);
            return  ResponseEntity.ok(new ApiResponse("Successfully Deleted",null));

        } catch (ResourceNotFoundException e){
            return  ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }



    @GetMapping("/product/by-name")
    public  ResponseEntity<ApiResponse> getProductsByName(@RequestParam  String name){


        try {
            List<Product> products = productService.getProductsByName(name);
            if(products.isEmpty()){
                return  ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No product found with name: "+name,null));
            }

            List<ProductDto> convertedProducts = productService.getConvertedProduct(products);
            return  ResponseEntity.ok(new ApiResponse("Successfully",convertedProducts));

        } catch (Exception e){
            return  ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("/by/brand-and-name")
    public  ResponseEntity<ApiResponse> getProductsByBrandName(@RequestParam  String brandName ,@RequestParam  String name){


        try {
            List<Product> products = productService.getProductsByBrandAndName(brandName,name);

            if(products.isEmpty()){
                return  ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No product found with name: "+name,null));
            }
            List<ProductDto> convertedProducts = productService.getConvertedProduct(products);
            return  ResponseEntity.ok(new ApiResponse("Successfully",convertedProducts));
//            return  ResponseEntity.ok(new ApiResponse("Successfully",products));

        } catch (Exception e){
            return  ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/product/by-brand")
    public  ResponseEntity<ApiResponse> getProductsByBrand(@RequestParam  String brandName){


        try {
            List<Product> products = productService.getProductBsyBrand(brandName);
            if(products.isEmpty()){
                return  ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No product found with brand: "+brandName,null));
            }
            List<ProductDto> convertedProducts = productService.getConvertedProduct(products);
            return  ResponseEntity.ok(new ApiResponse("Successfully",convertedProducts));
//            return  ResponseEntity.ok(new ApiResponse("Successfully",products));

        } catch (Exception e){
            return  ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/product/{category}")
    public  ResponseEntity<ApiResponse> getProductsByCategory(@PathVariable  String category){


        try {
            List<Product> products = productService.getProductsByCategory(category);
            if(products.isEmpty()){
                return  ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No product found with Category: "+category,null));
            }
            List<ProductDto> convertedProducts = productService.getConvertedProduct(products);
            return  ResponseEntity.ok(new ApiResponse("Successfully",convertedProducts));
           // return  ResponseEntity.ok(new ApiResponse("Successfully",products));

        } catch (Exception e){
            return  ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/product/count/by-brand-name")
    public  ResponseEntity<ApiResponse> countProductsByBrandAndName(@RequestParam  String brandName,@RequestParam  String name){


        try {
            var productcount = productService.countProductsByBrandAndName(brandName,name);

            return  ResponseEntity.ok(new ApiResponse("Successfully",productcount));

        } catch (Exception e){
            return  ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }



}

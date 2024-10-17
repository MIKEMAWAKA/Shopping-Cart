package com.mike.shoppingcart.controller;


import com.mike.shoppingcart.exceptions.ResourceNotFoundException;
import com.mike.shoppingcart.model.Category;
import com.mike.shoppingcart.response.ApiResponse;
import com.mike.shoppingcart.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories(){

        try {
            List<Category> categories = categoryService.getAllCategories();

            return ResponseEntity.ok(new ApiResponse("Found",categories));


        } catch (Exception e){
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error: ",INTERNAL_SERVER_ERROR));
        }



    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category){

        try {
            Category thecategory  = categoryService.addCategory(category);

            return ResponseEntity.ok(new ApiResponse("Successfully added",thecategory));


        } catch (Exception e){
            return  ResponseEntity.status(CONFLICT)
                    .body(new ApiResponse(e.getMessage(),null));
        }


    }


    @GetMapping("/category{id}/category")
    public  ResponseEntity<ApiResponse> getCategoryById(@PathVariable  Long id){


        try {
            Category thecategory = categoryService.getCategoryById(id);

            return  ResponseEntity.ok(new ApiResponse("Successfully",thecategory));

        } catch (ResourceNotFoundException e){
            return  ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }




    @GetMapping("/category{name}/category")
    public  ResponseEntity<ApiResponse> getCategorybyName(@PathVariable  String name){


        try {
            Category thecategory = categoryService.getCategoryByName(name);

            return  ResponseEntity.ok(new ApiResponse("Successfully",thecategory));

        } catch (ResourceNotFoundException e){
            return  ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }

    @DeleteMapping("/category{id}/delete")
    public  ResponseEntity<ApiResponse> deleteCategory(@PathVariable  Long id){


        try {
             categoryService.deleteCategoryById(id);
            return  ResponseEntity.ok(new ApiResponse("Successfully Deleted",null));

        } catch (ResourceNotFoundException e){
            return  ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }


    @PutMapping("/category{id}/update")
    public  ResponseEntity<ApiResponse> updateCategory(@PathVariable  Long id, @RequestBody Category category){


        try {
            Category thecategory =  categoryService.updateCategory(category,id);

            return  ResponseEntity.ok(new ApiResponse("Successfully",thecategory));

        } catch (ResourceNotFoundException e){
            return  ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }
}

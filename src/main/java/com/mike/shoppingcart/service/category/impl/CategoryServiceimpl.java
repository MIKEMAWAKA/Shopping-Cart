package com.mike.shoppingcart.service.category.impl;

import com.mike.shoppingcart.exceptions.ProductNotFoundException;
import com.mike.shoppingcart.exceptions.ResourceNotFoundException;
import com.mike.shoppingcart.model.Category;
import com.mike.shoppingcart.reposistory.CategoryRepository;
import com.mike.shoppingcart.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceimpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(
                        () ->new ResourceNotFoundException("Category Not Found")
                );
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category).filter(
                cate->!categoryRepository.existsByName(cate.getName())
        ).map(categoryRepository::save).
                orElseThrow(()-> new ResourceNotFoundException(category.getName()+" : Category Already Exists"));
    }

    @Override
    public Category updateCategory(Category category,Long id) {
        return Optional.ofNullable(getCategoryById(id))
                .map(oldCategory->{
                   oldCategory.setName(category.getName());

                   return  categoryRepository.save(oldCategory);
                }).orElseThrow(
                        ()->new ResourceNotFoundException("Category not found")
                );
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete,
                ()->{throw new ResourceNotFoundException("Category not found!");});



    }
}

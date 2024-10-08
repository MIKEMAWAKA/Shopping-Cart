package com.mike.shoppingcart.service.images.impl;

import com.mike.shoppingcart.exceptions.ProductNotFoundException;
import com.mike.shoppingcart.exceptions.ResourceNotFoundException;
import com.mike.shoppingcart.model.Image;
import com.mike.shoppingcart.reposistory.ImageRepository;
import com.mike.shoppingcart.service.images.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class ImageServiceimpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Image File Not Found"));
    }

    @Override
    public void deleteImageByID(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete,
                ()->{throw new ResourceNotFoundException("Image File not found!");});


    }

    @Override
    public Image saveImage(MultipartFile file, Long productId) {
        return null;
    }

    @Override
    public void updateImage(MultipartFile file, Long ImageId) {

    }
}

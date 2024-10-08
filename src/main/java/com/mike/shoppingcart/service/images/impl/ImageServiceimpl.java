package com.mike.shoppingcart.service.images.impl;

import com.mike.shoppingcart.dto.ImageDto;
import com.mike.shoppingcart.exceptions.ProductNotFoundException;
import com.mike.shoppingcart.exceptions.ResourceNotFoundException;
import com.mike.shoppingcart.model.Image;
import com.mike.shoppingcart.model.Product;
import com.mike.shoppingcart.reposistory.ImageRepository;
import com.mike.shoppingcart.service.images.ImageService;
import com.mike.shoppingcart.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageServiceimpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;


    @Autowired
    private ProductService productService;

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
    public Image saveImage(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);

        List<ImageDto> imageDtos = new ArrayList<>();
        for(MultipartFile file:files){

            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);
                String downloadUrl = "/api/v1/images/image/download/"+image.getId();
                image.setDownloadUrl(downloadUrl);
                Image savedimage =imageRepository.save(image);
                savedimage.setDownloadUrl("/api/v1/images/image/download/"+image.getId());

            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }


        }

        return null;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);

        try {
            image.setFileName(file.getOriginalFilename());
//            image.se(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}

package com.mike.shoppingcart.service.images;

import com.mike.shoppingcart.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    Image getImageById(Long id);

    void deleteImageByID(Long id);

    Image saveImage(MultipartFile file, Long productId);

    void updateImage(MultipartFile file,Long ImageId);



}

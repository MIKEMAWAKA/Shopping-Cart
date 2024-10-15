package com.mike.shoppingcart.service.images;

import com.mike.shoppingcart.dto.ImageDto;
import com.mike.shoppingcart.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    Image getImageById(Long id);

    void deleteImageByID(Long id);

    List<ImageDto> saveImage(List<MultipartFile> file, Long productId);

    void updateImage(MultipartFile file,Long ImageId);



}

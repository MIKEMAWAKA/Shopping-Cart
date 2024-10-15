package com.mike.shoppingcart.controller;


import com.mike.shoppingcart.dto.ImageDto;
import com.mike.shoppingcart.exceptions.ResourceNotFoundException;
import com.mike.shoppingcart.model.Image;
import com.mike.shoppingcart.response.ApiResponse;
import com.mike.shoppingcart.service.images.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/images")
public class ImageController {

    @Autowired
    private ImageService imageService;


    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files,@RequestParam Long productId){

        try {
            List<ImageDto> imageDtos = imageService.saveImage(files,productId);

            return ResponseEntity.ok(new ApiResponse("Upload successfully",imageDtos));


        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Upload failed! ",e.getMessage()));
        }

    }
@GetMapping("/image/download/{imageId}")
public  ResponseEntity<Resource> downloadImage(@PathVariable Long imageId){



//    try {

    try {
        Image image = imageService.getImageById(imageId);
        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1,(int) image.getImage().length()));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" +image.getFileName()+"\"").
                body(resource);

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }



//
//    } catch (Exception e){
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(new ApiResponse("failed! ",e.getMessage()));
//    }

}





    @PutMapping("/image/{imageId}/update")
    public ResponseEntity<ApiResponse>  updatedImage(@PathVariable Long imageId,@RequestBody MultipartFile file) {


        try {
            Image image = imageService.getImageById(imageId);
            if (image != null) {
                imageService.updateImage(file, imageId);

                return ResponseEntity.ok(new ApiResponse("Update successfully", image));

            }
        } catch (ResourceNotFoundException e) {

            return ResponseEntity.status(NOT_FOUND).body(
                    new ApiResponse(e.getMessage(), null)
            );
        }

        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(new ApiResponse("Update failed! ",INTERNAL_SERVER_ERROR));

    }

    @DeleteMapping("/image/{imageId}/delete")
    public ResponseEntity<ApiResponse>  deleteImage(@PathVariable Long imageId) {


        try {
            Image image = imageService.getImageById(imageId);
            if (image != null) {
                imageService.deleteImageByID(imageId);

                return ResponseEntity.ok(new ApiResponse("Deleted successfully", null));

            }
        } catch (ResourceNotFoundException e) {

            return ResponseEntity.status(NOT_FOUND).body(
                    new ApiResponse(e.getMessage(), null)
            );
        }

        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(new ApiResponse("Delete failed! ",INTERNAL_SERVER_ERROR));

    }


}

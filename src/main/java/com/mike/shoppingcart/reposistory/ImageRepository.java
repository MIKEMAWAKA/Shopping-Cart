package com.mike.shoppingcart.reposistory;

import com.mike.shoppingcart.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}

package com.mike.shoppingcart.reposistory;

import com.mike.shoppingcart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
}

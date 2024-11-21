package com.mike.shoppingcart.service.users;

import com.mike.shoppingcart.dto.UserDto;
import com.mike.shoppingcart.model.User;
import com.mike.shoppingcart.request.CreateUserReq;
import com.mike.shoppingcart.request.UserUpdateReq;

public interface UserService {

    User getUserById(Long userId);

    User createUser(CreateUserReq userReq);

    User updateUser(UserUpdateReq userUpdateReq, Long userId);

    void deleteUser(Long id);


    UserDto convertUserToDto(User user);
}

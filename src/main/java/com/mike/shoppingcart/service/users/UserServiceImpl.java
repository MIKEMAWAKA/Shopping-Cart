package com.mike.shoppingcart.service.users;

import com.mike.shoppingcart.dto.UserDto;
import com.mike.shoppingcart.exceptions.ResourceNotFoundException;
import com.mike.shoppingcart.model.User;
import com.mike.shoppingcart.reposistory.UserRepository;
import com.mike.shoppingcart.request.CreateUserReq;
import com.mike.shoppingcart.request.UserUpdateReq;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ModelMapper modelMapper;


    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
            throw new ResourceNotFoundException("User not found");
        });
    }

    @Override
    public User createUser(CreateUserReq userReq) {

        return Optional.of(userReq).filter(user->!userRepository.existsByEmail(userReq.getEmail()))
                .map(req -> {
                    User user = new User();
                    user.setEmail(userReq.getEmail());
                    user.setPassword(userReq.getPassword());
                    user.setFirstname(userReq.getFirstname());
                    user.setLastname(userReq.getLastname());
                    return  userRepository.save(user);
                }) .orElseThrow(() -> new ResourceNotFoundException("Oops!" +userReq.getEmail() +" already exists!"));
    }

    @Override
    public User updateUser(UserUpdateReq userUpdateReq, Long userId) {

      return   userRepository.findById(userId).map(
                existingUser->{
                    existingUser.setFirstname(userUpdateReq.getFirstname());
                    existingUser.setLastname(userUpdateReq.getLastname());
                    return  userRepository.save(existingUser);
                }
        ).orElseThrow(()->{
            throw  new ResourceNotFoundException("No user found with Id to update");
        });

//        return null;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository::delete, () -> {
            throw new ResourceNotFoundException("User not found");

        });
    }


    @Override
    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}

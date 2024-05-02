package com.readowo.api.ReadOwo.Services.IServices;

import com.readowo.api.ReadOwo.Models.User;
import com.readowo.api.ReadOwo.Services.Communication.UserResponse;
import com.readowo.api.ReadOwo.dtos.SaveUserDto;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> getAllUsers();
    User getUserById(Long userId);
    User saveUser(User user);
    UserResponse deleteUser(Long userId);
    UserResponse updateUser(Long id, SaveUserDto saveUserDtos);
}

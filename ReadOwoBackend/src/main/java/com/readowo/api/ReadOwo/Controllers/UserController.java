package com.readowo.api.ReadOwo.Controllers;

import com.readowo.api.ReadOwo.Models.User;
import com.readowo.api.ReadOwo.Services.Communication.UserResponse;
import com.readowo.api.ReadOwo.Services.ServicesImpl.UserServiceImpl;
import com.readowo.api.ReadOwo.dtos.SaveUserDto;
import com.readowo.api.ReadOwo.dtos.UserDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOneUser(@PathVariable(value = "id") Long userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody SaveUserDto saveUserDtos) {
        User user = modelMapper.map(saveUserDtos, User.class);
        User createdUser = userService.saveUser(saveUserDtos);
        UserDto userDtos = modelMapper.map(createdUser, UserDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id, @RequestBody SaveUserDto saveUserDtos) {
        UserResponse response = userService.updateUser(id, saveUserDtos);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable(value = "id") Long userId) {
        UserResponse response = userService.deleteUser(userId);
        return ResponseEntity.ok().body(response);
    }
}

package com.readowo.api.ReadOwo.Controllers;

import com.readowo.api.ReadOwo.Models.User;
import com.readowo.api.ReadOwo.Services.Communication.UserResponse;
import com.readowo.api.ReadOwo.Services.IServices.IUserService;
import com.readowo.api.ReadOwo.Services.ServicesImpl.UserServiceImpl;
import com.readowo.api.ReadOwo.dtos.SaveUserDto;
import com.readowo.api.ReadOwo.dtos.UserDto;
import com.readowo.api.ReadOwo.mapping.UserMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/user", produces = "application/json")
public class UserController {
    private final IUserService userService;
    private final ModelMapper modelMapper;
    private final UserMapper mapper;
    public UserController(IUserService userService, ModelMapper modelMapper, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
        this.modelMapper = modelMapper;
    }
    @GetMapping
    public Page<UserDto> getAllUsers(Pageable pageable) {
        return mapper.modelListPage(userService.getAllUsers(), pageable);
    }

    @GetMapping("{userId}")
    public UserDto getOneUser(@PathVariable Long userId) {
        return mapper.toResource(userService.getUserById(userId));
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

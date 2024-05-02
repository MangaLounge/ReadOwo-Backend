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
@RequestMapping(value = "/api/v1/users", produces = "application/json")
public class UserController {
    private final IUserService userService;
    private final UserMapper mapper;
    public UserController(IUserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }
    @GetMapping
    public List<UserDto> getAllUsers() {
        return mapper.modelList(userService.getAllUsers());
    }

    @GetMapping("{userId}")
    public UserDto getOneUser(@PathVariable Long userId) {
        return mapper.toResource(userService.getUserById(userId));
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody SaveUserDto saveUserDtos) {
        return new ResponseEntity<>(mapper.toResource(userService.saveUser(mapper.toModel(saveUserDtos))), HttpStatus.CREATED);
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

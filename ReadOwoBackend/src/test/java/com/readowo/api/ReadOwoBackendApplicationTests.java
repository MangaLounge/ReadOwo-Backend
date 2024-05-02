package com.readowo.api;

import com.readowo.api.ReadOwo.Controllers.UserController;
import com.readowo.api.ReadOwo.Models.User;
import com.readowo.api.ReadOwo.Services.Communication.UserResponse;
import com.readowo.api.ReadOwo.Services.IServices.IUserService;
import com.readowo.api.ReadOwo.dtos.SaveUserDto;
import com.readowo.api.ReadOwo.dtos.UserDto;
import com.readowo.api.ReadOwo.mapping.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
class ReadOwoBackendApplicationTests {
    private IUserService userService;
    private ModelMapper modelMapper;
    private UserMapper userMapper;
    private UserController userController;

    @BeforeEach
    void setUp() {
        userService = new IUserService() {
            @Override
            public List<User> getAllUsers() {
                return null;
            }

            @Override
            public User getUserById(Long userId) {
                return null;
            }

            @Override
            public User saveUser(User user) {
                return null;
            }

            @Override
            public UserResponse deleteUser(Long userId) {
                return null;
            }

            @Override
            public UserResponse updateUser(Long id, SaveUserDto saveUserDtos) {
                return null;
            }
        }; // Utilizamos una implementación falsa para IUserService
        modelMapper = new ModelMapper();
        userMapper = new UserMapper();
        userController = new UserController(userService, userMapper);
    }
    @Test
    void testGetOneUser() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setPassword("testpassword");

        // Act
        UserDto actualUserDto = userController.getOneUser(userId);

        // Assert
        assertEquals(user.getName(), actualUserDto.getName());
        assertEquals(user.getEmail(), actualUserDto.getEmail());
        assertEquals(user.getPassword(), actualUserDto.getPassword());
    }
    @Test
    void testSaveUser() {
        // Arrange
        SaveUserDto saveUserDto = new SaveUserDto();
        saveUserDto.setName("Test User");
        saveUserDto.setEmail("test@example.com");
        saveUserDto.setPassword("testpassword");

        // Act
        ResponseEntity<UserDto> responseEntity = userController.saveUser(saveUserDto);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        UserDto savedUserDto = responseEntity.getBody();
        assertEquals(saveUserDto.getName(), savedUserDto.getName());
        assertEquals(saveUserDto.getEmail(), savedUserDto.getEmail());
        assertEquals(saveUserDto.getPassword(), savedUserDto.getPassword());
    }

    @Test
    void testUpdateUser() {
        // Arrange
        Long userId = 1L;
        SaveUserDto updateUserDto = new SaveUserDto();
        updateUserDto.setName("Updated Test User");
        updateUserDto.setEmail("updatedtest@example.com");
        updateUserDto.setPassword("updatedtestpassword");

        // Act
        ResponseEntity<UserResponse> responseEntity = userController.updateUser(userId, updateUserDto);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Updated Test User", responseEntity.getBody().getMessage());
    }

    @Test
    void testDeleteUser() {
        // Arrange
        Long userId = 1L;

        // Act
        ResponseEntity<UserResponse> responseEntity = userController.deleteUser(userId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User not found.", responseEntity.getBody().getMessage());
    }
}

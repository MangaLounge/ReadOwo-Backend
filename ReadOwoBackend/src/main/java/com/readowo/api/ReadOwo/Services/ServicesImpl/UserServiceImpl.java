package com.readowo.api.ReadOwo.Services.ServicesImpl;

import com.readowo.api.ReadOwo.Models.User;
import com.readowo.api.ReadOwo.Repositories.UserRepository;
import com.readowo.api.ReadOwo.Services.Communication.UserResponse;
import com.readowo.api.ReadOwo.Services.IServices.IUserService;
import com.readowo.api.ReadOwo.dtos.ResourceNotFoundException;
import com.readowo.api.ReadOwo.dtos.SaveUserDto;
import com.readowo.api.ReadOwo.dtos.UserDto;
import com.readowo.api.Shared.Persistence.Repositories.UnitOfWork;
import com.readowo.api.publishing.Repositories.BookRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final UnitOfWork unitOfWork;

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private static final String ENTITY = "User";



    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public User saveUser(SaveUserDto saveUserDtos) {
        User user = modelMapper.map(saveUserDtos, User.class);
        return userRepository.save(user);
    }

    @Override
    public UserResponse deleteUser(Long userId) {

        Optional<User> existingUser = userRepository.findById(userId);
        if (!existingUser.isPresent()) {
            return new UserResponse("User not found.");
        }

        try {
            userRepository.delete(existingUser.get());
            unitOfWork.complete();
            return new UserResponse(existingUser.get());
        } catch (Exception e) {
            return new UserResponse("An error occurred while deleting the user: " + e.getMessage());
        }
    }

    @Override
    public UserResponse updateUser(Long id, SaveUserDto saveUserDtos) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            modelMapper.map(saveUserDtos, existingUser);
            User updatedUser = userRepository.save(existingUser);
            UserDto userDtos = modelMapper.map(updatedUser, UserDto.class);
            return new UserResponse(String.valueOf(userDtos));
        } else {
            return new UserResponse("User not found");
        }
    }
}

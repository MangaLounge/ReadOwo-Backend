package com.readowo.api.ReadOwo.Controllers;

import com.readowo.api.ReadOwo.Models.UserProfile;
import com.readowo.api.ReadOwo.Services.Communication.UserProfileResponse;
import com.readowo.api.ReadOwo.Services.ServicesImpl.UserProfileServiceImpl;
import com.readowo.api.ReadOwo.dtos.SaveUserProfileDto;
import com.readowo.api.ReadOwo.dtos.UserProfileDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/userProfile")
@AllArgsConstructor
public class UserProfileController {
    private final UserProfileServiceImpl userProfileService;
    private final ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<List<UserProfile>> getAllUserProfiles() {
        List<UserProfile> userProfiles = userProfileService.getAllUserProfiles();
        return ResponseEntity.ok().body(userProfiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getOneUserProfile(@PathVariable(value = "id") Long userProfileId) {
        Optional<UserProfile> userProfile = userProfileService.getUserProfileById(userProfileId);
        return userProfile.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserProfileDto> saveUserProfile(@RequestBody SaveUserProfileDto saveUserProfileDto) {
        UserProfile userProfile = modelMapper.map(saveUserProfileDto, UserProfile.class);
        UserProfile createdUserProfile = userProfileService.saveUserProfile(saveUserProfileDto);
        UserProfileDto userProfileDto = modelMapper.map(createdUserProfile, UserProfileDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(userProfileDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfileResponse> updateUserProfile(@PathVariable("id") Long id, @RequestBody SaveUserProfileDto saveUserProfileDto) {
        UserProfileResponse response = userProfileService.updateUserProfile(id, saveUserProfileDto);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserProfileResponse> deleteUserProfile(@PathVariable(value = "id") Long userProfileId) {
        UserProfileResponse response = userProfileService.deleteUserProfile(userProfileId);
        return ResponseEntity.ok().body(response);
    }
}

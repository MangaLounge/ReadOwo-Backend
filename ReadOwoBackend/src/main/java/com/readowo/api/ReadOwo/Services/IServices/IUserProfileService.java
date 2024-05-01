package com.readowo.api.ReadOwo.Services.IServices;

import com.readowo.api.ReadOwo.Models.UserProfile;
import com.readowo.api.ReadOwo.Services.Communication.UserProfileResponse;
import com.readowo.api.ReadOwo.dtos.SaveUserProfileDto;

import java.util.List;
import java.util.Optional;

public interface IUserProfileService {

    List<UserProfile> getAllUserProfiles();
    Optional<UserProfile> getUserProfileById(Long userProfileId);
    UserProfile saveUserProfile(SaveUserProfileDto saveUserProfileDto);
    UserProfileResponse deleteUserProfile(Long userProfileId);
    UserProfileResponse updateUserProfile(Long id, SaveUserProfileDto saveUserProfileDto);
}

package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.dtos.UserProfileDto;
import nl.novi.eindopdrachtv3.models.User;
import nl.novi.eindopdrachtv3.models.UserProfile;

import java.util.List;

public interface UserProfileService {
    List<UserProfileDto> getAllUserProfiles();
    UserProfileDto getUserProfileById(Long id);
    UserProfileDto createUserProfile(UserProfileDto dto);
    void deleteUserProfile(Long id);
    UserProfileDto updateUserProfile(Long id, UserProfileDto dto);
    void assignImageToProfile(Long profileId, String fileName);
    void assignUserToUserProfile(Long id, String username);

//    void assignImageToUserProfile(Long id, Long imageId);

    // UserProfileDto getUserProfileByUsername(User username);
    // is dit mogelijk??
}

package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.dtos.UserProfileDto;
import nl.novi.eindopdrachtv3.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtv3.models.Image;
import nl.novi.eindopdrachtv3.models.UserProfile;
import nl.novi.eindopdrachtv3.repositories.ImageRepository;
import nl.novi.eindopdrachtv3.repositories.UserProfileRepository;
import nl.novi.eindopdrachtv3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExamServiceImpl examService;

    @Override
    public List<UserProfileDto> getAllUserProfiles() {
        List<UserProfile> upsList = userProfileRepository.findAll();
        List<UserProfileDto> dtos = new ArrayList<>();
        for (UserProfile up : upsList) {
            dtos.add(fromUserPrToDto(up));
        }
        return dtos;
    }

    @Override
    public UserProfileDto getUserProfileById(Long id) {
        if (userProfileRepository.findById(id).isPresent()){
            UserProfile up = userProfileRepository.findById(id).get();
            UserProfileDto dto = fromUserPrToDto(up);
            return dto;
        } else {
            throw new RecordNotFoundException("geen user profiel gevonden");
        }
    }

    @Override
    public UserProfileDto createUserProfile(UserProfileDto dto) {
        UserProfile up = fromDtoToUserPr(dto);
        userProfileRepository.save(up);

        UserProfileDto newDto = fromUserPrToDto(up);

        return newDto;
    }

    @Override
    public void deleteUserProfile(Long id) {
        examService.deleteAllExamsByUserProfile(id);

        UserProfile up = userProfileRepository.getById(id);
        up.setExams(null);

        userProfileRepository.deleteById(id);
    }

    @Override
    public UserProfileDto updateUserProfile(Long id, UserProfileDto dto) {
        if (userProfileRepository.findById(id).isPresent()) {

            UserProfile up = userProfileRepository.findById(id).get();
            if (dto.getFirstName() != null) {
                up.setFirstName(dto.getFirstName());
            }
            if (dto.getLastName() != null) {
                up.setLastName(dto.getLastName());
            }
            if (dto.getAge() != null) {
                up.setAge(dto.getAge());
            }
            if (dto.getSchool() != null) {
                up.setSchool(dto.getSchool());
            }
            userProfileRepository.save(up);

            return dto;
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void assignImageToProfile(Long profileId, String fileName) {
        Optional<UserProfile> optionalProfile = userProfileRepository.findById(profileId);
        Optional<Image> optImg = imageRepository.findByFileName(fileName);

        if (optionalProfile.isPresent() && optImg.isPresent()) {
            UserProfile profile = optionalProfile.get();
            Image img = optImg.get();

            profile.setProfilePic(img);
            userProfileRepository.save(profile);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void assignUserToUserProfile(Long id, String username) {
        var optionalUserProfile = userProfileRepository.findById(id);
        var optionalUser = userRepository.findById(username);

        if(optionalUserProfile.isPresent() && optionalUser.isPresent()) {
            var userProfile = optionalUserProfile.get();
            var user = optionalUser.get();

            userProfile.setUsername(user);
            userProfileRepository.save(userProfile);
        } else {
            throw new RecordNotFoundException();
        }
    }


    // methods for transition between dto & entities
    public static UserProfileDto fromUserPrToDto(UserProfile up){
        var dto = new UserProfileDto();

        dto.setId(up.getId());
        dto.setFirstName(up.getFirstName());
        dto.setLastName(up.getLastName());
        dto.setAge(up.getAge());
        dto.setSchool(up.getSchool());
        dto.setUsername(up.getUsername());
        dto.setProfilePic(up.getProfilePic());
        dto.setExams(up.getExams());

        return dto;
    }

    public UserProfile fromDtoToUserPr(UserProfileDto upDto) {
        var up = new UserProfile();

//        up.setId(upDto.getId());
        up.setFirstName(upDto.getFirstName());
        up.setLastName(upDto.getLastName());
        up.setAge(upDto.getAge());
        up.setSchool(upDto.getSchool());
        up.setUsername(upDto.getUsername());
        up.setProfilePic(upDto.getProfilePic());
        up.setExams(upDto.getExams());

        return up;
    }

}

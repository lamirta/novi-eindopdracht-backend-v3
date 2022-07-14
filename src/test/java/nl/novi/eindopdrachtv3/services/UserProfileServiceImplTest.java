package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.dtos.UserProfileDto;
import nl.novi.eindopdrachtv3.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtv3.models.Authority;
import nl.novi.eindopdrachtv3.models.Image;
import nl.novi.eindopdrachtv3.models.User;
import nl.novi.eindopdrachtv3.models.UserProfile;
import nl.novi.eindopdrachtv3.repositories.ImageRepository;
import nl.novi.eindopdrachtv3.repositories.UserProfileRepository;
import nl.novi.eindopdrachtv3.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.ArrayList;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserProfileServiceImplTest {

    @Autowired
    private UserProfileServiceImpl userProfileServiceImpl;

    @MockBean
    private UserProfileRepository userProfileRepository;

    @MockBean
    private ImageRepository imageRepository;

    @MockBean
    UserServiceImpl userServiceImpl;

    @Mock
    UserRepository userRepository;

    @Mock
    UserProfile userProfile;

    @Mock
    UserProfileDto userProfileDto;

    @Mock
    User user;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setFirstName("jan");
        userProfile.setLastName("jansen");
        user = new User();
        user.setUsername("jantje");
        user.setPassword("password");
        user.setEmail("test@email.nl");
        userProfileDto = new UserProfileDto();
    }


    @Test
    void testMethodGetAllUserProfiles() {
        userProfileServiceImpl.getAllUserProfiles();
        verify(userProfileRepository).findAll();
    }

    @Test
    void testGetUserProfileById() {
        // Act / When
        Mockito
                .when(userProfileRepository.findById(userProfile.getId()))
                .thenReturn(Optional.of(userProfile));

        Optional<UserProfile> userProfileFound = userProfileRepository.findById(1L);
        userProfileServiceImpl.getUserProfileById(userProfile.getId());

        // Assert / Then
        Assertions.assertEquals(1, userProfileFound.get().getId());
    }


    @Test
    void testCreateUserProfile() {
        Authority authority = new Authority();
        authority.setAuthority("JaneDoe");
        authority.setUsername("janedoe");

        UserProfile userProfile = new UserProfile();
        userProfile.setAge(1);
        userProfile.setExams(new ArrayList<>());
        userProfile.setFirstName("Jane");
        userProfile.setId(123L);
        userProfile.setLastName("Doe");
        userProfile.setProfilePic(new Image());
        userProfile.setSchool("School");
        userProfile.setUsername(new User());

        User user = new User();
        user.addAuthority(authority);
        user.setEmail("jane.doe@test.nl");
        user.setEnabled(true);
        user.setPassword("password");
        user.setUserProfile(userProfile);
        user.setUsername("janedoe");

        Image image = new Image();
        image.setFileName("img.png");
        image.setType("Type");
        image.setUrl("https://example.org/example");

        when(this.userProfileRepository.save((UserProfile) org.mockito.Mockito.any())).thenReturn(userProfile);
        UserProfileDto upDto = this.userProfileServiceImpl.createUserProfile(new UserProfileDto());

        verify(this.userProfileRepository).save((UserProfile) org.mockito.Mockito.any());
    }


    @Test
    void testDeleteUserProfile() {
        doNothing().when(this.userProfileRepository)
                .deleteById((Long) org.mockito.Mockito.any());

        this.userProfileServiceImpl.deleteUserProfile(1L);

        verify(this.userProfileRepository).deleteById((Long) org.mockito.Mockito.any());
        assertTrue(this.userProfileServiceImpl.getAllUserProfiles().isEmpty());
    }

    @Test
    void testUpdateUserProfile() {
        userProfile.setUsername(user);
        userProfile.setId(123L);
        userProfile.setFirstName("Jane");
        userProfile.setLastName("Doe");
        userProfile.setSchool("School");
        userProfile.setAge(10);

        Optional<UserProfile> optionalUp = Optional.of(userProfile);

        when(this.userProfileRepository.save((UserProfile) any())).thenReturn(userProfile);
        when(this.userProfileRepository.findById((Long) any())).thenReturn(optionalUp);

        UserProfileDto userProfileDto = new UserProfileDto();

        assertSame(userProfileDto, this.userProfileServiceImpl.updateUserProfile(123L, userProfileDto));
        verify(this.userProfileRepository).save((UserProfile) any());
        verify(this.userProfileRepository, atLeast(1)).findById((Long) any());
    }

    @Test
    void testUpdateUserProfileException() {
        userProfile.setUsername(user);
        userProfile.setId(123L);
        userProfile.setFirstName("Jane");
        userProfile.setLastName("Doe");
        userProfile.setSchool("School");
        userProfile.setAge(10);

        Optional<UserProfile> optionalUp = Optional.of(userProfile);
        when(this.userProfileRepository.save((UserProfile) any()))
                .thenThrow(new RecordNotFoundException());
        when(this.userProfileRepository.findById((Long) any())).thenReturn(optionalUp);
        assertThrows(RecordNotFoundException.class,
                () -> this.userProfileServiceImpl.updateUserProfile(123L, new UserProfileDto()));
        verify(this.userProfileRepository).save((UserProfile) any());
        verify(this.userProfileRepository, atLeast(1)).findById((Long) any());
    }


    @Test
    void nonExistingUpIdShouldReturnExceptionInGetById() {
        UserProfileDto dto = new UserProfileDto(
                2L
        );
        given(userProfileRepository.existsById(dto.getId()))
                .willReturn(false);

        assertThatThrownBy(() -> userProfileServiceImpl.getUserProfileById(dto.getId()))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("geen user profiel gevonden");
    }


    @Test
    void testAssignImageToProfile() {
        Image image = new Image();
        image.setFileName("image.jpg");
        image.setType("Type");
        image.setUrl("https://example.org/example");

        userProfile.setAge(1);
        userProfile.setExams(new ArrayList<>());
        userProfile.setFirstName("Jane");
        userProfile.setId(123L);
        userProfile.setLastName("Doe");
        userProfile.setProfilePic(image);
        userProfile.setSchool("School");
        userProfile.setUsername(user);

        Optional<UserProfile> optUP = Optional.of(userProfile);
        when(this.userProfileRepository.save((UserProfile) org.mockito.Mockito.any())).thenReturn(userProfile);
        when(this.userProfileRepository.findById((Long) org.mockito.Mockito.any())).thenReturn(optUP);

        Optional<Image> optImg = Optional.of(image);
        when(this.imageRepository.findByFileName((String) org.mockito.Mockito.any())).thenReturn(optImg);

        this.userProfileServiceImpl.assignImageToProfile(123L, "image.jpg");

        verify(this.userProfileRepository).save(userProfile);
        verify(this.userProfileRepository).findById(123L);
        verify(this.imageRepository).findByFileName("image.jpg");
    }


    @Test
    void shouldTestMethodFromUserPrToDto() {
        UserProfile up = new UserProfile();

        up.setId(1L);
        up.setFirstName("jan");
        up.setAge(10);

        UserProfileDto dto = UserProfileServiceImpl.fromUserPrToDto(up);

        assertEquals(up.getId(), dto.getId());
        assertEquals(up.getFirstName(), dto.getFirstName());
        assertEquals(up.getAge(), dto.getAge());

    }

}
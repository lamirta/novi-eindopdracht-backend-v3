package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.Eindopdrachtv3Application;
import nl.novi.eindopdrachtv3.dtos.UserProfileDto;
import nl.novi.eindopdrachtv3.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtv3.models.User;
import nl.novi.eindopdrachtv3.models.UserProfile;
import nl.novi.eindopdrachtv3.repositories.UserProfileRepository;
import nl.novi.eindopdrachtv3.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserProfileServiceImplTest {

    @InjectMocks
    private UserProfileServiceImpl userProfileService;

    @MockBean
    private UserProfileRepository userProfileRepository;

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
        userProfileService.getAllUserProfiles();
        verify(userProfileRepository).findAll();
    }

    @Test
    void testGetUserProfileById() {
        // Arrange / Given
//        userProfile.setUsername(user);


        // Act / When
        Mockito
                .when(userProfileRepository.findById(userProfile.getId()))
                .thenReturn(Optional.of(userProfile));

        Optional<UserProfile> userProfileFound = userProfileRepository.findById(1L);
        userProfileService.getUserProfileById(userProfile.getId());

        // Assert / Then
        Assertions.assertEquals(1, userProfileFound.get().getId());
    }


    @Test
    void nonExistingUpIdShouldReturnExceptionInGetById() {
        // given
        UserProfileDto dto = new UserProfileDto(
                2L
        );
        given(userProfileRepository.existsById(dto.getId()))
                .willReturn(false);

        // when
        // then
        assertThatThrownBy(() ->userProfileService.getUserProfileById(dto.getId()))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("geen user profiel gevonden");
    }



    @Test
    void shouldTestMethodAssignUserToUserProfile() {
        // Arrange / Given
        UserProfile up = new UserProfile();
        up.setId(1L);
        up.setFirstName("jan");

        User user = new User();
        user.setUsername("jantje123");

        Mockito
                .when(userProfileRepository.findById(up.getId()))
                .thenReturn(Optional.of(up));
        Mockito
                .when(userRepository.findById(user.getUsername()))
                .thenReturn(Optional.of(user));

        Optional<UserProfile> upFound = userProfileRepository.findById(up.getId());
        Optional<User> userFound = userRepository.findById(user.getUsername());

//      // Act / When
        userProfileService.assignUserToUserProfile(upFound.get().getId(), userFound.get().getUsername());
        UserProfile found = upFound.get();

        // Assert / Then
        assertEquals("jantje123", found.getUsername().getUsername());
    }

    @Test
    void testCreateUserProfile() {
        Mockito.when(userProfileRepository.save(any())).thenReturn(userProfile);

        UserProfileDto dto = userProfileService.createUserProfile(UserProfileServiceImpl.fromUserPrToDto(userProfile));

        Long upId = dto.getId();

        assertEquals(1L, upId);
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

    @Test
    void shouldTestMethodFromDtoToUserPr() {
        UserProfileDto dto = new UserProfileDto();

        dto.setId(2L);
        dto.setFirstName("piet");
        dto.setAge(12);

        UserProfile up = userProfileService.fromDtoToUserPr(dto);

        assertEquals(dto.getId(), up.getId());
        assertEquals(dto.getFirstName(), up.getFirstName());
        assertEquals(dto.getAge(), up.getAge());
    }
}
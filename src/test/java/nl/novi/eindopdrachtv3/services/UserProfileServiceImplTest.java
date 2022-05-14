package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.Eindopdrachtv3Application;
import nl.novi.eindopdrachtv3.dtos.UserProfileDto;
import nl.novi.eindopdrachtv3.models.User;
import nl.novi.eindopdrachtv3.models.UserProfile;
import nl.novi.eindopdrachtv3.repositories.UserProfileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserProfileServiceImplTest {

    @InjectMocks
    UserProfileServiceImpl userProfileService;

    @Mock
    UserProfileRepository userProfileRepository;

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
    void testGetUserProfileById() {
        // Arrange / Given
        userProfile.setUsername(user);

        Mockito
                .when(userProfileRepository.findById(userProfile.getId()))
                .thenReturn(Optional.of(userProfile));

        // Act / When
        Optional<UserProfile> userProfileFound = userProfileRepository.findById(1L);

        // Assert / Then
        Assertions.assertEquals(userProfileFound.get().getId(), 1 );


    }



//    @Test
//    void testFromUserPrToDto() {
////        userProfileDto = new UserProfileDto();
//
//        Mockito
//                .when(userProfileRepository.findById(userProfile.getId()))
//                .thenReturn(userProfileDto);
//
//        assertEquals("Jan", "");
//    }

    @Test
    void fromDtoToUserPr() {

    }
}
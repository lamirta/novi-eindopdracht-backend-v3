//package nl.novi.eindopdrachtv3.services;
//
//import nl.novi.eindopdrachtv3.Eindopdrachtv3Application;
//import nl.novi.eindopdrachtv3.models.UserProfile;
//import nl.novi.eindopdrachtv3.repositories.UserProfileRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//
//public class CommentsNotes {
//}
//
//
//package nl.novi.eindopdrachtv3.services;
//
//        import nl.novi.eindopdrachtv3.Eindopdrachtv3Application;
//        import nl.novi.eindopdrachtv3.models.UserProfile;
//        import nl.novi.eindopdrachtv3.repositories.UserProfileRepository;
//        import org.junit.jupiter.api.BeforeEach;
//        import org.junit.jupiter.api.Test;
//        import org.mockito.Mock;
//        import org.mockito.Mockito;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.boot.context.properties.EnableConfigurationProperties;
//        import org.springframework.boot.test.context.SpringBootTest;
//        import org.springframework.boot.test.mock.mockito.MockBean;
//        import org.springframework.test.context.ContextConfiguration;
//
//        import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@ContextConfiguration(classes={Eindopdrachtv3Application.class})
//@EnableConfigurationProperties
//class UserProfileServiceImplTest {
//
//    @Autowired
//    private UserProfileService userProfileService;
//
//    @MockBean
//    private UserProfileRepository userProfileRepository;
//
//    @Mock
//    UserProfile userProfile;
//
//
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @Test
//    void getAllUserProfiles() {
//    }
//
////    @Test
////    void testGetUserProfileById() {
////        userProfile = new UserProfile(1);
////
////        Mockito
////                .when(userProfileRepository.findById(1))
////                .thenReturn(userProfile);
////
////        String id = "1";
////        String expected = "1";
////
////        userProfile found = userProfileService.getUserProfileById(id);
////
////        assertEquals(expected, found.getId());
////
////    }
//
//    @Test
//    void fromUserPrToDto() {
//    }
//
//    @Test
//    void fromDtoToUserPr() {
//    }
//}

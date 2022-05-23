package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.dtos.UserDto;
import nl.novi.eindopdrachtv3.dtos.UserProfileDto;
import nl.novi.eindopdrachtv3.exceptions.BadRequestException;
import nl.novi.eindopdrachtv3.models.User;
import nl.novi.eindopdrachtv3.models.UserProfile;
import nl.novi.eindopdrachtv3.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@DataJpaTest deze moet in Repository test
class UserServiceImplTest {

    @Mock private UserRepository userRepository;
    private AutoCloseable autoCloseable;
    private UserServiceImpl userServiceTest;
    @Captor
    ArgumentCaptor<User> userArgumentCaptor;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userServiceTest = new UserServiceImpl(userRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testMethodGetAllUsers() {
        userServiceTest.getUsers();
        verify(userRepository).findAll();
    }

    @Test
    void testMethodCreateUser() {
        // given
        User user = new User(
                "jantje123",
                "password",
                "jantje@test.nl"
        );
        // when
        userRepository.save(user);
        // then

        verify(userRepository,times(1)).save(userArgumentCaptor.capture());

//        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(capturedUser.getPassword()).isEqualTo(user.getPassword());
        assertThat(capturedUser.getEmail()).isEqualTo(user.getEmail());
    }



    @Test
    void testThrowsExWhenUsernameIsTaken() {
        // given
        UserDto dto = new UserDto(
                "jantje123",
                "password",
                "jantje@test.nl",
                true,
                null
        );
        given(userRepository.existsById(dto.getUsername()))
                .willReturn(true);

        // when
        // then
        assertThatThrownBy(() ->userServiceTest.createUser(dto))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("username" + dto.getUsername() + "taken");

        verify(userRepository, never()).save(any());

    }

    @Test
    void shouldTestMethodFromDtoToUser() {
        UserDto dto = new UserDto();

        dto.setUsername("jantje123");
        dto.setPassword("password");
        dto.setEmail("jantje@test.nl");

        User u = userServiceTest.fromDtoToUser(dto);

        assertEquals(dto.getUsername(), u.getUsername());
        assertEquals(dto.getPassword(), u.getPassword());
        assertEquals(dto.getEmail(), u.getEmail());
    }

    @Test
    void shouldTestMethodFromUserToDto() {
        User u = new User();

        u.setUsername("jantje123");
        u.setPassword("password");
        u.setEmail("jantje@test.nl");

        UserDto dto = userServiceTest.fromUserToDto(u);

        assertEquals(u.getUsername(), dto.getUsername());
        assertEquals(u.getPassword(), dto.getPassword());
        assertEquals(u.getEmail(), dto.getEmail());
    }

    @Test
    void setUserEnabled() {
    }
}









//    @Test
//    void testMethodCreateUser() {
//        // given
//        UserDto dto = new UserDto(
//                "jantje123",
//                "password",
//                "jantje@test.nl",
//                true,
//                null
//        );
//        // when
//        userServiceTest.createUser(dto);
//        // then
//        ArgumentCaptor<User> userArgumentCaptor =
//                ArgumentCaptor.forClass(User.class);
//        verify(userRepository).save(userArgumentCaptor.capture());
//        User capturedUser = userArgumentCaptor.getValue();
//        assertThat(capturedUser).isEqualTo(dto);
//    }


// hoe krijg ik dit goed met dto & entity door elkaar?

//    @Test
//    void testMethodGetUserByUsername() {
//        String username = "jantje123";
//
//        Mockito
//                .doReturn(null).when(userRepository)
//                .findById(username);
//
//        // Execute the service call
//        UserDto found = userServiceTest.getUserByUsername(username);
//
//        // Assert the response
//        assertNull(found, "Widget should not be found");
//    }
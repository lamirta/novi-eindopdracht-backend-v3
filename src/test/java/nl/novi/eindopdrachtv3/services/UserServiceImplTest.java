package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.dtos.UserDto;
import nl.novi.eindopdrachtv3.models.User;
import nl.novi.eindopdrachtv3.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
//@DataJpaTest deze moet in Repository test
class UserServiceImplTest {

    @Mock private UserRepository userRepository;
    private AutoCloseable autoCloseable;
    private UserServiceImpl userServiceTest;

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
//        ArgumentCaptor<UserDto> userDtoArgumentCaptor =
//                ArgumentCaptor.forClass(UserDto.class);
//        ArgumentCaptor<User> userArgumentCaptor =
//                ArgumentCaptor.forClass(User.class);
//        User newUser = User userArgumentCaptor;
//
//        verify(userRepository).save(userArgumentCaptor.capture());
//        User capturedUser = userArgumentCaptor.getValue();
//        assertThat(capturedUser).isEqualTo(dto);
//    }

    @Test
    void testThrowsExWhenUsernameIsTaken() {

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
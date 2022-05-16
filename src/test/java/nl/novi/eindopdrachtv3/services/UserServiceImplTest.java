package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.dtos.UserDto;
import nl.novi.eindopdrachtv3.exceptions.BadRequestException;
import nl.novi.eindopdrachtv3.models.User;
import nl.novi.eindopdrachtv3.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
//@DataJpaTest deze moet in Repository test
class UserServiceImplTest {

    @Mock private UserRepository userRepository;
    @Mock User user;
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
////        ArgumentCaptor<User> userArgumentCaptor =
////                ArgumentCaptor.forClass(User.class);
//        User newUser = User userArgumentCaptor;
//
//        verify(userRepository).save(userDtoArgumentCaptor.capture());
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
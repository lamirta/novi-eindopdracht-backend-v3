package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.dtos.UserDto;
import nl.novi.eindopdrachtv3.exceptions.BadRequestException;
import nl.novi.eindopdrachtv3.exceptions.UsernameNotFoundException;
import nl.novi.eindopdrachtv3.models.Authority;
import nl.novi.eindopdrachtv3.models.User;
import nl.novi.eindopdrachtv3.models.UserProfile;
import nl.novi.eindopdrachtv3.repositories.UserProfileRepository;
import nl.novi.eindopdrachtv3.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @MockBean
    private UserRepository userRepository;
    private UserProfileServiceImpl userProfileServiceImpl;
    private UserProfileRepository userProfileRepository;
    private AutoCloseable autoCloseable;
    private PasswordEncoder passwordEncoder;

    @Captor
    ArgumentCaptor<User> userArgumentCaptor;

    @Mock
    User user;

    @Mock
    UserDto dto;

    @Mock
    UserProfile userProfile;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        passwordEncoder = new BCryptPasswordEncoder();
        userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setFirstName("jan");
        userProfile.setLastName("jansen");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testMethodGetAllUsers() {
        userServiceImpl.getUsers();
        verify(userRepository).findAll();
    }

    @Test
    void testGetUserByUsername() {
        User user = new User(
                "jantje123",
                "password",
                "jantje@test.nl"
        );

        Mockito
                .when(userRepository.findById(user.getUsername()))
                .thenReturn(Optional.of(user));

        Optional<User> uFound = userRepository.findById("jantje123");
        userServiceImpl.getUserByUsername(user.getUsername());

        String expected = "jantje123";

        assertEquals(expected, uFound.get().getUsername());
    }


    @Test
    void testCreateUser() {
        Authority authority = new Authority();
        authority.setAuthority("JaneDoe");
        authority.setUsername("janedoe");

        user.addAuthority(authority);
        user.setEmail("jane.doe@test.com");
        user.setEnabled(true);
        user.setPassword("password");
        user.setUserProfile(userProfile);
        user.setUsername("janedoe");

        when(this.userRepository.existsById("janedoe")).thenReturn(true);
        when(this.userRepository.save((User) org.mockito.Mockito.any())).thenReturn(user);
        assertThrows(BadRequestException.class,
                () -> this.userServiceImpl.createUser(new UserDto("janedoe", "password", "jane.doe@test.com")));
        verify(this.userRepository).existsById("janedoe");
    }


    @Test
    void testAddAuthority() {
        Authority authority = new Authority();
        authority.setAuthority("JaneDoe");
        authority.setUsername("janedoe");

        userProfile.setId(123L);
        userProfile.setUsername(new User());

        user.addAuthority(authority);
        user.setUsername("janedoe");
        user.setPassword("password");
        user.setEmail("jane.doe@test.com");
        user.setEnabled(true);
        user.setUserProfile(userProfile);
        Optional<User> optUser = Optional.of(user);

        when(this.userRepository.save((User) org.mockito.Mockito.any())).thenReturn(user);
        when(this.userRepository.findById((String) org.mockito.Mockito.any())).thenReturn(optUser);
        when(this.userRepository.existsById((String) org.mockito.Mockito.any())).thenReturn(true);

        this.userServiceImpl.addAuthority("janedoe", "JaneDoe");

        verify(this.userRepository).existsById((String) org.mockito.Mockito.any());
        verify(this.userRepository).save(user);
        verify(this.userRepository).findById("janedoe");
    }


    @Test
    void testRemoveAuthority() {
        Authority authority = new Authority();
        authority.setAuthority("JaneDoe");
        authority.setUsername("janedoe");

        UserProfile up = new UserProfile();
        up.setId(123L);
        up.setUsername(new User());

        User user = new User();
        user.addAuthority(authority);
        user.setUsername("janedoe");
        user.setEmail("jane.doe@test.nl");
        user.setPassword("password");
        user.setEnabled(true);
        user.setUserProfile(up);

        Optional<User> optionalUser = Optional.of(user);

        when(this.userRepository.save((User) org.mockito.Mockito.any())).thenReturn(user);
        when(this.userRepository.findById((String) org.mockito.Mockito.any())).thenReturn(optionalUser);
        when(this.userRepository.existsById((String) org.mockito.Mockito.any())).thenReturn(true);

        this.userServiceImpl.removeAuthority("janedoe", "JaneDoe");

        verify(this.userRepository).existsById((String) org.mockito.Mockito.any());
        verify(this.userRepository).save((User) org.mockito.Mockito.any());
        verify(this.userRepository).findById((String) org.mockito.Mockito.any());
    }



    @Test
    void nonExistingUsernameShouldReturnExceptionInGetByUsername() {
        // given
        UserDto dto = new UserDto(
                "jantje12",
                "passwordd",
                "jantjeeee@test.nl"
        );
        given(userRepository.existsById(dto.getUsername()))
                .willReturn(false);

        // when
        // then
        assertThatThrownBy(() -> userServiceImpl.getUserByUsername(dto.getUsername()))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining(dto.getUsername());
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

        // when // then
        assertThatThrownBy(() -> userServiceImpl.createUser(dto))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("username " + dto.getUsername() + " taken");

        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldTestMethodFromUserToDto() {
        User u = new User();

        u.setUsername("jantje123");
        u.setPassword("password");
        u.setEmail("jantje@test.nl");

        UserDto dto = userServiceImpl.fromUserToDto(u);

        assertEquals(u.getUsername(), dto.getUsername());
        assertEquals(u.getPassword(), dto.getPassword());
        assertEquals(u.getEmail(), dto.getEmail());
    }

}

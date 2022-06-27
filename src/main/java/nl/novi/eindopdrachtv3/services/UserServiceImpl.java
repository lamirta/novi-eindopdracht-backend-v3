package nl.novi.eindopdrachtv3.services;

import lombok.AllArgsConstructor;
import nl.novi.eindopdrachtv3.dtos.UserDto;
import nl.novi.eindopdrachtv3.exceptions.BadRequestException;
import nl.novi.eindopdrachtv3.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtv3.exceptions.UsernameNotFoundException;
import nl.novi.eindopdrachtv3.models.Authority;
import nl.novi.eindopdrachtv3.models.Exam;
import nl.novi.eindopdrachtv3.models.User;
import nl.novi.eindopdrachtv3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    public UserDto getUserByUsername(String username) {
        UserDto udto = new UserDto();
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()){
            User u = userRepository.findById(username).get();
            udto.setUsername(u.getUsername());
            udto.setPassword(u.getPassword());
            udto.setEmail(u.getEmail());
            udto.setUserProfile(u.getUserProfile());
            udto.setEnabled(u.isEnabled());
            udto.setAuthorities(u.getAuthorities());
            return udto;
        }else {
            throw new UsernameNotFoundException(username);
        }
    }

    public List<UserDto> getUsers() {
        List<User> ul = userRepository.findAll();
        List<UserDto> udtoList = new ArrayList<>();

        for (User u : ul) {
            UserDto udto = new UserDto(u.getUsername(), u.getPassword(), u.getEmail(), u.isEnabled(), u.getUserProfile(), u.getAuthorities());
            udtoList.add(udto);
        }
        return udtoList;
    }

//    if (data.getPassword() != null) {
//        user.setPassword(passwordEncoder.encode(data.getPassword().get()));
//    }

    public UserDto createUser(UserDto userDto) {
        boolean existsUsername = userRepository.existsById(userDto.getUsername());
        if (existsUsername) {
            throw new BadRequestException("username " + userDto.getUsername() + " taken");
        }
        User newUser = fromDtoToUser(userDto);
        newUser.addAuthority(new Authority(newUser.getUsername(), "ROLE_USER"));
        newUser.setEnabled(true);
        userRepository.save(newUser);
        return userDto;
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public void updateUser(String username, UserDto updatedUser) {
        if (!userRepository.existsById(username)) {
            throw new RecordNotFoundException();
        } else if (userRepository.existsById(updatedUser.getUsername())) {
            throw new BadRequestException("username " + updatedUser.getUsername() + " taken");
        } else {
        User user = userRepository.findById(username).get();
        if (updatedUser.getUsername() != null) {
            user.setUsername(updatedUser.getUsername());
        }
        if (updatedUser.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        if (updatedUser.getEmail() != null) {
                user.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getUserProfile() != null) {
                user.setUserProfile(updatedUser.getUserProfile());
        }
        userRepository.save(user);
        }
    }
//            else {
//            user.setUsername(user.getUsername());
//        }

    public void setUserEnabled(String username, UserDto updatedUser) {
        if (!userRepository.existsById(username)) {
            throw new UsernameNotFoundException(username);
        } else {
            User user = userRepository.findById(username).get();
            user.setUsername(user.getUsername());
            user.setEnabled(updatedUser.isEnabled());
            userRepository.save(user);
        }
    }

    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) {
            throw new UsernameNotFoundException(username);
        } else {
            User user = userRepository.findById(username).get();
            UserDto userDto = fromUserToDto(user);
            return userDto.getAuthorities();
        }
    }

    public void addAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) {
            throw new UsernameNotFoundException(username);
        } else {
            User user = userRepository.findById(username).get();
            user.addAuthority(new Authority(username, authority));
            userRepository.save(user);
        }
    }

    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }


    // methodes voor omzetten van dto naar entitie en andersom.
    public static UserDto fromUserToDto(User user){

        var dto = new UserDto();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.email = user.getEmail();
        dto.enabled = user.isEnabled();
        dto.userProfile = user.getUserProfile();
        dto.authorities = user.getAuthorities();

        return dto;
    }


    public User fromDtoToUser(UserDto userDto) {
        if (userDto.getPassword() != null) {
            var user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setEmail(userDto.getEmail());

            return user;
        } else {
            throw new BadRequestException("password cannot be empty");
        }
    }

    //    if (data.getPassword() != null) {
//        user.setPassword(passwordEncoder.encode(data.getPassword().get()));
//    }

//    public User fromDtoToUser(UserDto userDto) {
//
//        var user = new User();
//
//        user.setUsername(userDto.getUsername());
//        user.setPassword(userDto.getPassword());
//        user.setEmail(userDto.getEmail());
//        user.setEnabled(userDto.isEnabled());
//        user.addAuthority(new Authority(userDto.getUsername(), "ROLE_USER"));
//
//        return user;
//    }

}

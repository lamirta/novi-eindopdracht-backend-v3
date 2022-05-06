package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.dtos.UserDto;
import nl.novi.eindopdrachtv3.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtv3.exceptions.UsernameNotFoundException;
import nl.novi.eindopdrachtv3.models.Authority;
import nl.novi.eindopdrachtv3.models.User;
import nl.novi.eindopdrachtv3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public UserDto getUserByUsername(String username) {
        UserDto udto = new UserDto();
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()){
            User u = userRepository.findById(username).get();
            udto.setUsername(u.getUsername());
            udto.setPassword(u.getPassword());
            udto.setEmail(u.getEmail());
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
            UserDto udto = new UserDto(u.getUsername(), u.getPassword(), u.getEmail(), u.isEnabled(), u.getAuthorities());
            udtoList.add(udto);
        }
        return udtoList;
    }

    public UserDto createUser(UserDto userDto) {
        User newUser = fromDtoToUser(userDto);
        userRepository.save(newUser);
        return userDto;
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public void updateUser(String username, UserDto updatedUser) {
        if (!userRepository.existsById(username)) {
            throw new RecordNotFoundException();
        } else {
        User user = userRepository.findById(username).get();
        user.setPassword(updatedUser.getPassword());
        user.setEmail(updatedUser.getEmail());
        userRepository.save(user);
        }
    }

    public void setUserEnabled(String username, UserDto updatedUser) {
        if (!userRepository.existsById(username)) {
            throw new UsernameNotFoundException(username);
        } else {
            User user = userRepository.findById(username).get();
            user.setEnabled(updatedUser.isEnabled());
            user.setEmail(updatedUser.getEmail());
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

        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
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
        dto.authorities = user.getAuthorities();

        return dto;
    }

    public User fromDtoToUser(UserDto userDto) {

        var user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setEnabled(userDto.isEnabled());

        return user;
    }

}

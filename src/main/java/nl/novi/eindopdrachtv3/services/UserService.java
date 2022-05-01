package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.dtos.UserDto;
import nl.novi.eindopdrachtv3.exceptions.UsernameNotFoundException;
import nl.novi.eindopdrachtv3.models.User;
import nl.novi.eindopdrachtv3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto getUser(String username) {
        UserDto udto = new UserDto();
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()){
            User u = userRepository.findById(username).get();
            udto.setUsername(u.getUsername());
            udto.setPassword(u.getPassword());
            udto.setEmail(u.getEmail());
            udto.setApikey(u.getApikey());
            udto.setEnabled(u.getEnabled());
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
            UserDto udto = new UserDto(u.getUsername(), u.getPassword(), u.getEmail(), u.getEnabled(), u.getApikey(), u.getAuthorities());
            udtoList.add(udto);
        }
        return udtoList;
    }

}

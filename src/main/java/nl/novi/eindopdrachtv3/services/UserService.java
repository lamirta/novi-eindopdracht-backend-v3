package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.dtos.UserDto;
import nl.novi.eindopdrachtv3.models.Authority;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserDto getUserByUsername(String username);
    List<UserDto> getUsers();
    UserDto createUser(UserDto userDto);
    void deleteUser(String username);
    void updateUser(String username, UserDto updatedUser);
    void setUserEnabled(String username, UserDto updatedUser);
    Set<Authority> getAuthorities(String username);
    void addAuthority(String username, String authority);
    void removeAuthority(String username, String authority);
}

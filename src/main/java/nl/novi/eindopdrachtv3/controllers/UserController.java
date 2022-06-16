package nl.novi.eindopdrachtv3.controllers;

import nl.novi.eindopdrachtv3.dtos.UserDto;
import nl.novi.eindopdrachtv3.exceptions.BadRequestException;
import nl.novi.eindopdrachtv3.services.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class UserController {
    private final UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }


    @GetMapping("/users/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable("username") String username) {
        UserDto udto = service.getUserByUsername(username);
        return ResponseEntity.ok().body(udto);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> allUsers = service.getUsers();
        return ResponseEntity.ok().body(allUsers);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDto dto) {
        service.createUser(dto);

        return new ResponseEntity<>("User aangemaakt!", HttpStatus.CREATED);
    }

    @PutMapping("/users/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("username") String username, @RequestBody UserDto dto) {
        service.updateUser(username, dto);
//        String message = "user is updated";
        return ResponseEntity.ok().build();
    }

    // denk dat deze het niet doet bij testen.. of misschien een PUT met IdInputDto?
    @PutMapping("/users/{username}/enabled")
    public ResponseEntity<UserDto> setUserEnabled(@PathVariable("username") String username, @RequestBody UserDto dto) {
        service.setUserEnabled(username, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String username) {
        service.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(service.getAuthorities(username));
    }

    @PostMapping("/users/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String authorityRole = (String)fields.get("authority");
            service.addAuthority(username, authorityRole);
            return ResponseEntity.noContent().build();
        }
        catch (Exception exc) {
            throw new BadRequestException("Bad Request Error for adding new authority role to user");
        }
    }

    @DeleteMapping("/users/{username}/authorities/{authority}")
    public ResponseEntity<Object> removeUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        service.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }

}

package nl.novi.eindopdrachtv3.controllers;

import nl.novi.eindopdrachtv3.services.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }



}

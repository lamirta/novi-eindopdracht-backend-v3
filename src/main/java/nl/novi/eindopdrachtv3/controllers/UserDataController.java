package nl.novi.eindopdrachtv3.controllers;

import nl.novi.eindopdrachtv3.services.UserDataService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDataController {
    private final UserDataService service;

    public UserDataController(UserDataService service) {
        this.service = service;
    }

}

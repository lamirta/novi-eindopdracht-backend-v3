package nl.novi.eindopdrachtv3.controllers;

import nl.novi.eindopdrachtv3.dtos.IdInputDto;
import nl.novi.eindopdrachtv3.dtos.UserProfileDto;
import nl.novi.eindopdrachtv3.models.Image;
import nl.novi.eindopdrachtv3.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class UserProfileController {

    private final UserProfileService service;
    private final ImageController imageController;

    @Autowired
    public UserProfileController(UserProfileService service, ImageController imageController) {
        this.service = service;
        this.imageController = imageController;
    }

    @GetMapping("/userprofiles")
    public ResponseEntity<List<UserProfileDto>> getAllUserProfiles() {
        List<UserProfileDto> allUserProfiles = service.getAllUserProfiles();
        return ResponseEntity.ok().body(allUserProfiles);
    }

    @GetMapping("/userprofiles/{id}")
    public ResponseEntity<UserProfileDto> getUserProfileById(@PathVariable("id") Long id) {
        UserProfileDto up = service.getUserProfileById(id);
        return ResponseEntity.ok().body(up);
    }

    @PostMapping("/userprofiles")
    public ResponseEntity<UserProfileDto> createUserProfile(@Valid @RequestBody UserProfileDto dto) {
        UserProfileDto newUserProfile = service.createUserProfile(dto);
        return ResponseEntity.ok().body(newUserProfile);
    }

    @PutMapping("/userprofiles/{id}")
    public ResponseEntity<UserProfileDto> updateUserProfile(@PathVariable("id") Long id, @RequestBody UserProfileDto dto) {
        service.updateUserProfile(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/userprofiles/{id}")
    public ResponseEntity<Object> deleteUserProfile(@PathVariable("id") Long id) {
        service.deleteUserProfile(id);
        return ResponseEntity.noContent().build();
    }

    // Not needed in current FE, iniciated while creating User class
    @PutMapping("/userprofiles/{id}/username")
    public void assignUserToUserProfile(@PathVariable("id") Long id, @RequestBody IdInputDto input) {
        service.assignUserToUserProfile(id, input.username);
    }

    @PostMapping("/userprofiles/{id}/image")
    public void assignImageToProfile(@PathVariable("id") Long profileId,
                                     @RequestBody MultipartFile file) {

        Image image = imageController.uploadImage(file);
        service.assignImageToProfile(profileId, image.getFileName());
    }
}

package nl.novi.eindopdrachtv3.controllers;

import nl.novi.eindopdrachtv3.dtos.WordListDto;
import nl.novi.eindopdrachtv3.models.Image;
import nl.novi.eindopdrachtv3.services.ImageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {
    private final ImageService service;

    public ImageController(ImageService service) {
        this.service = service;
    }


    //produces = MediaType.MULTIPART_MIXED_VALUE
    @GetMapping(value = "/images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImageById(@PathVariable String imageName) {
        Image img = service.getImageById(imageName).get();
        return img.image;
    }

    @PostMapping("/images")
    public String uploadImage(@RequestBody MultipartFile file) {
        return service.uploadImage(file);
        // whitedragon fileUpload even checken, single upload > filesystem controller.
        // Geef URL terug, wanneer ze die afbeelding uploaden.
    }

    @DeleteMapping(value = "/images/{imageName}")
    public void deleteImage(@PathVariable("imageName") String imageName) {

        service.deleteImage(imageName);
    }

    @PutMapping("/image/{imageName}")
    public ResponseEntity<Image> updateImage(@PathVariable("imageName") String imageName, @RequestBody MultipartFile file) {
//        Image img = service.updateImage(imageName, file);
//        return img;
          service.updateImage(imageName, file);
          return ResponseEntity.noContent().build();
//        return img.image;service.updateImage(imageName, file);
    }

}



//    @GetMapping(value = "/images/{username}", produces = MediaType.IMAGE_JPEG_VALUE)
//    public @ResponseBody byte[] getImageByUsername(@PathVariable String username) {
//        Image img = imageRepository.findById(id).get();
//        return img.image;
//    }
//
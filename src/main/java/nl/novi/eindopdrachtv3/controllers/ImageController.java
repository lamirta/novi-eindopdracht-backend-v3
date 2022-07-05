package nl.novi.eindopdrachtv3.controllers;

import nl.novi.eindopdrachtv3.models.Image;
import nl.novi.eindopdrachtv3.services.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@CrossOrigin
@RestController
public class ImageController {
    private final ImageService service;

    public ImageController(ImageService service) {
        this.service = service;
    }

    @GetMapping(value = "/images/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImageById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getImageById(id));
    }

    @GetMapping("/images")
    public Collection<Image> getAllImages(){
        return service.getAllImages();
    }

    @PostMapping("/images")
    Image uploadImage(@RequestParam("file") @RequestBody MultipartFile file){
        Image newImage = service.uploadImage(file);
//        return new ResponseEntity<>("Image uploaded!", HttpStatus.CREATED);
        return new Image(newImage.getId());
    }

    @DeleteMapping("/images/{id}")
    public void deleteImage(@PathVariable("id") Long id) {
        service.deleteImage(id);
    }

}


      // hier eindje opweg voor local file storage system

//    @GetMapping(value = "/images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
//    ResponseEntity<Resource> getImageByImageName(@PathVariable String imageName, HttpServletRequest request) {
//        Resource resource = service.getImageById(imageName);
//
//        Image img = service.getImageById(imageName).get();
//        return img.image;
//    }
//
//
//    @PostMapping("/images/uploads")
//    ImageUploadResponse uploadImage(@RequestParam("file") MultipartFile file) {
//
//        String imageName = service.storeImage(file);
//        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(imageName).toUriString();
//        String mediaType = file.getContentType();
//
//        return new ImageUploadResponse(imageName, mediaType, url);
//    }

      // hier eventueel nog naar kijken

//    //produces = MediaType.MULTIPART_MIXED_VALUE
//    @GetMapping(value = "/images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
//    public @ResponseBody byte[] getImageByImageName(@PathVariable String imageName) {
//        Image img = service.getImageById(imageName).get();
//        return img.image;
//    }


//    @PutMapping("/image/{imageName}")
//    public ResponseEntity<Image> updateImage(@PathVariable("imageName") String imageName, @RequestBody MultipartFile file) {
////        Image img = service.updateImage(imageName, file);
////        return img;
//          service.updateImage(imageName, file);
//          return ResponseEntity.noContent().build();
////        return img.image;service.updateImage(imageName, file);
//    }


//    @GetMapping(value = "/images/{username}", produces = MediaType.IMAGE_JPEG_VALUE)
//    public @ResponseBody byte[] getImageByUsername(@PathVariable String username) {
//        Image img = imageRepository.findById(id).get();
//        return img.image;
//    }
//
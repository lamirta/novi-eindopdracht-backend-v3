package nl.novi.eindopdrachtv3.controllers;

import nl.novi.eindopdrachtv3.models.Image;
import nl.novi.eindopdrachtv3.services.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;

@CrossOrigin
@RestController
public class ImageController {
    private final ImageService service;

    public ImageController(ImageService service) {
        this.service = service;
    }

        @GetMapping(value = "/images/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
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



//    @GetMapping(value = "/images/{id}")
//    ResponseEntity<Resource> getImageById(@PathVariable Long id, HttpServletRequest request) {
//
//        Resource resource = service.getImageById(id);
//
////        this mediaType decides witch type you accept if you only accept 1 type
////        MediaType contentType = MediaType.IMAGE_JPEG;
////        this is going to accept multiple types
//        String mimeType;
//
//        try{
//            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException e) {
//            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
//        }
//
////        for download attachment use next line
////        return ResponseEntity.ok().contentType(contentType).header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + resource.getFilename()).body(resource);
////        for showing image in browser
//        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename()).body(resource);
//    }




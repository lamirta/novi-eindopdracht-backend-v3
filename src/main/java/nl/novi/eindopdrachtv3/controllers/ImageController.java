package nl.novi.eindopdrachtv3.controllers;

import nl.novi.eindopdrachtv3.dtos.WordListDto;
import nl.novi.eindopdrachtv3.models.Image;
import nl.novi.eindopdrachtv3.services.ImageService;
import nl.novi.eindopdrachtv3.utils.ImageUploadResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@CrossOrigin
@RestController
public class ImageController {
    private final ImageService service;

    public ImageController(ImageService service) {
        this.service = service;
    }


    @GetMapping(value = "/images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<Resource> getImageByImageName(@PathVariable String imageName, HttpServletRequest request) {
        Resource resource = service.getImageById(imageName);

        Image img = service.getImageById(imageName).get();
        return img.image;
    }

    @GetMapping("/download/{fileName}")
    ResponseEntity<Resource> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = fileStorageService.downLoadFile(fileName);

//        this mediaType decides witch type you accept if you only accept 1 type
//        MediaType contentType = MediaType.IMAGE_JPEG;
//        this is going to accept multiple types
        String mimeType;

        try{
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

//        for download attachment use next line
//        return ResponseEntity.ok().contentType(contentType).header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + resource.getFilename()).body(resource);
//        for showing image in browser
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename()).body(resource);
    }




    @PostMapping("/images/uploads")
    ImageUploadResponse uploadImage(@RequestParam("file") MultipartFile file) {

        String imageName = service.storeImage(file);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(imageName).toUriString();
        String mediaType = file.getContentType();

        return new ImageUploadResponse(imageName, mediaType, url);
    }



    @DeleteMapping(value = "/images/{imageName}")
    public void deleteImage(@PathVariable("imageName") String imageName) {

        service.deleteImage(imageName);
    }



}



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
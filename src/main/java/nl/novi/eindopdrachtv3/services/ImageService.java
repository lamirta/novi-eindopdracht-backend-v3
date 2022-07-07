package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.models.Image;
import nl.novi.eindopdrachtv3.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.Objects;

@Service
public class ImageService {

    @Value("${my.upload_location}")
    private Path fileStoragePath;
    private final String fileStorageLocation;

    private final ImageRepository imageRepository;

    public ImageService(@Value("${my.upload_location}") String fileStorageLocation, ImageRepository imageRepository) {
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();

        this.fileStorageLocation = fileStorageLocation;
        this.imageRepository = imageRepository;

        try {
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Issue while creating file directory");
        }
    }

    public String uploadImage(MultipartFile file, String url){
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        Path filePath = Paths.get(fileStoragePath + "\\" + fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Issue in storing the file", e);
        }

        // wordt id opgeslagen nu / aangemaakt in storage?
        imageRepository.save(new Image(fileName, file.getContentType(), url));

        return fileName;
    }

    public Resource getImageByName(String fileName) {
        Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);

        Resource resource;

        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Issue in reading file", e);
        }

        if(resource.exists()&& resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("file doesn't exist or not readable");
        }
    }


    public Collection<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public void deleteImage(String fileName){
        imageRepository.deleteById(fileName);
    }

}


//    public byte[] getImageById(Long id) {
//        if (!imageRepository.existsById(id)) {
//            throw new RecordNotFoundException("Afbeelding niet gevonden.");
//        } else {
//            Image img = imageRepository.findById(id).get();
//            return img.image;
//        }
//    }



//    public String updateImage(String imageName, MultipartFile file){
//        if (imageRepository.findById(imageName).isPresent()) {
//            Image img = new Image(img.setImageName(img.getImageName());
//
//            return img;
//        } else {
//            throw new RecordNotFoundException("Afbeelding niet gevonden.");
//        }
//    }


//
//    public Image uploadImage(MultipartFile file){
//        Image img = new Image();
//        try {
//            img.setImage(file.getBytes());
//            img.setType(file.getContentType());
//            imageRepository.save(img);
//        } catch (IOException iex){
//            throw new RuntimeException("Error tijdens opslaan");
//        }
//        return img;
//    }


//        Image img = new Image();
//        img.setMediaType(file.getContentType()); //dit moet dan in de service opgeslagen worden..
//        var a = file.getResource();
//        try {
//            img.image = file.getBytes();
//        }
//        catch (IOException iex){
//            return "Error while uploading image"; // kan deze error nog specificeren.
//        }
//        imageRepository.save(img);  //die methode aanmaken in service
//        return "Image uploaded";

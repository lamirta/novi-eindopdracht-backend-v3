package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtv3.models.Image;
import nl.novi.eindopdrachtv3.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

// ImageService is voor Storage in local file system, niet in de Database, om ruimte te besparen.
@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;


    @Value("${my.upload_location}")
    private Path fileStrongPath;
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


    public Optional<Image> getImageById(String imageName) {
        if (!imageRepository.existsById(imageName)) { throw new RecordNotFoundException("Afbeelding niet gevonden."); }
        return imageRepository.findById(imageName);
    }

    public String uploadImage (MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path filePath = Paths.get(fileStrongPath + "\\" + fileName);
        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException iex){
            throw new RuntimeException("Error tijdens opslaan");
        }
        return fileName;
    }

    // werkt deze wel goed? even testen nog.
    public void deleteImage(String imageName){

        imageRepository.deleteById(imageName);
    }

//    public String updateImage(String imageName, MultipartFile file){
//        if (imageRepository.findById(imageName).isPresent()) {
//            Image img = new Image(img.setImageName(img.getImageName());
//
//            return img;
//        } else {
//            throw new RecordNotFoundException("Afbeelding niet gevonden.");
//        }
//    }

}





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

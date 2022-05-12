package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtv3.models.Image;
import nl.novi.eindopdrachtv3.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;


@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;


    public byte[] getImageById(Long id) {
        if (!imageRepository.existsById(id)) {
            throw new RecordNotFoundException("Afbeelding niet gevonden.");
        } else {
            Image img = imageRepository.findById(id).get();
            return img.image;
        }
    }

    public Collection<Image> getAllImages() {
        return imageRepository.findAll();
    }


    public Image uploadImage(MultipartFile file){
        Image img = new Image();
        try {
            img.setImage(file.getBytes());
            img.setType(file.getContentType());
            imageRepository.save(img);
        } catch (IOException iex){
            throw new RuntimeException("Error tijdens opslaan");
        }
        return img;
    }

    public void deleteImage(Long id){
        imageRepository.deleteById(id);
    }

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

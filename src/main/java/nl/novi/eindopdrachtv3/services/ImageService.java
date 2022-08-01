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

//  Use this line to save file in directory for Windows:
//        Path filePath = Paths.get(fileStoragePath + "\\" + fileName);

//  Use this line to save file in directory for Mac:
        Path filePath = Paths.get(fileStoragePath + "/" + fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Issue in storing the file", e);
        }

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

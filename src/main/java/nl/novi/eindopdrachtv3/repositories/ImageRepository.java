package nl.novi.eindopdrachtv3.repositories;

import nl.novi.eindopdrachtv3.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {

//    Optional<Image> existsByName(String imageName);

}
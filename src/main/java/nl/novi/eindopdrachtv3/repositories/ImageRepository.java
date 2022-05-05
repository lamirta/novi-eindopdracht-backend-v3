package nl.novi.eindopdrachtv3.repositories;

import nl.novi.eindopdrachtv3.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ImageRepository extends JpaRepository<Image, String> {
    Image findByMediaType(String mediaType);

//    Optional<Image> existsByName(String imageName);

}

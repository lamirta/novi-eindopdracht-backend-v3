package nl.novi.eindopdrachtv3.repositories;

import nl.novi.eindopdrachtv3.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findMediaType(String mediaType);
//    Image existsByImageName(String imageName);

}

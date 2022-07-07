package nl.novi.eindopdrachtv3.repositories;

import nl.novi.eindopdrachtv3.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, String> {
//    Optional<Image> findById(Long id);
    Optional<Image> findByFileName(String fileName);
}

package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.dtos.ExamDto;
import nl.novi.eindopdrachtv3.models.Exam;
import nl.novi.eindopdrachtv3.models.Image;
import nl.novi.eindopdrachtv3.models.UserProfile;
import nl.novi.eindopdrachtv3.repositories.ExamRepository;
import nl.novi.eindopdrachtv3.repositories.ImageRepository;
import nl.novi.eindopdrachtv3.repositories.UserProfileRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ImageServiceTest {

    @InjectMocks
    ImageService mockService;

    @Mock
    ImageRepository mockRepository;

    @Mock
    Image image;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        image = new Image();
        image.setId(2L);
    }

    @Test
    void getImageById() {
        Mockito
                .when(mockRepository.findById(image.getId()))
                .thenReturn(Optional.of(image));

        Optional<Image> imageFound = mockRepository.findById(2L);

//        var expected = null
        var actual = imageFound.get().getId();

        // Assert / Then
        Assertions.assertEquals(2, actual);

    }

    @Test
    void TestGetAllImages() {
        mockService.getAllImages();
        verify(mockRepository).findAll();
    }
}
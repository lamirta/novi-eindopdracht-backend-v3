package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.Eindopdrachtv3Application;
import nl.novi.eindopdrachtv3.dtos.ExamDto;
import nl.novi.eindopdrachtv3.dtos.WordListDto;
import nl.novi.eindopdrachtv3.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtv3.exceptions.TitleNotFoundException;
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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ContextConfiguration(classes={Eindopdrachtv3Application.class})
@EnableConfigurationProperties
class ImageServiceTest {

    @InjectMocks
    private ImageService mockService;

    @MockBean
    private ImageRepository mockRepository;

    @Mock
    Image image;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        image = new Image(
                2L
        );

    }

    @Test
    void TestGetAllImages() {
        mockService.getAllImages();
        verify(mockRepository).findAll();
    }

    @Test
    void nonExistingExamIdShouldReturnExceptionInGetById() {

        given(mockRepository.existsById(image.getId()))
                .willReturn(false);

        // when
        // then
        assertThatThrownBy(() ->mockService.getImageById(image.getId()))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("Afbeelding niet gevonden.");
    }

    // weet niet hoe je het veld image (byte[]) invult)
//    @Test
//    void getImageById() {
//        Mockito
//                .when(mockRepository.findById(image.getId()))
//                .thenReturn(Optional.of(image));
//
//        Optional<Image> imageFound = mockRepository.findById(2L);
//
//        mockService.getImageById(image.getId());
//
//        // Assert / Then
//        assertEquals(image.getId(), imageFound.get().getId());
//
//    }

}
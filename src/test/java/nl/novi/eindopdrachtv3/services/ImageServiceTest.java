package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.Eindopdrachtv3Application;
import nl.novi.eindopdrachtv3.models.Image;
import nl.novi.eindopdrachtv3.repositories.ImageRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    @Disabled
    void TestGetAllImages() {
        mockService.getAllImages();
        verify(mockRepository).findAll();
    }

    @Test
    @Disabled
    void nonExistingImageNameShouldReturnExceptionInGetByName() {
        given(mockRepository.existsById(image.getFileName()))
                .willReturn(false);

        // when
        // then
        assertThatThrownBy(() ->mockService.getImageByName(image.getFileName()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("file doesn't exist or not readable");
    }

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
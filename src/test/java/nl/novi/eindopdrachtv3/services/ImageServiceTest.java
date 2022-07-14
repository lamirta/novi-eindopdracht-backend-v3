package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.Eindopdrachtv3Application;
import nl.novi.eindopdrachtv3.models.Image;
import nl.novi.eindopdrachtv3.repositories.ImageRepository;
import nl.novi.eindopdrachtv3.repositories.UserProfileRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    @MockBean
    private ImageRepository imageRepository;

    @Mock
    Image image;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        image = new Image(
                "image.jpeg"
        );
    }

    @Test
    void TestGetAllImages() {
        imageService.getAllImages();
        verify(imageRepository).findAll();
    }

    @Test
    void nonExistingImageNameShouldReturnExceptionInGetByName() {
        given(imageRepository.existsById(image.getFileName()))
                .willReturn(false);

        // when
        // then
        assertThatThrownBy(() ->imageService.getImageByName(image.getFileName()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("file doesn't exist or not readable");
    }

}
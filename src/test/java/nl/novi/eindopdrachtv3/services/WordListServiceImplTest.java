package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.Eindopdrachtv3Application;
import nl.novi.eindopdrachtv3.dtos.WordListDto;
import nl.novi.eindopdrachtv3.models.WordList;
import nl.novi.eindopdrachtv3.repositories.WordListRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ContextConfiguration(classes={Eindopdrachtv3Application.class})
@EnableConfigurationProperties
class WordListServiceImplTest {

    @Autowired
    private WordListService wordListService;

    @MockBean
    private WordListRepository wordListRepository;

    @Mock
    WordList wordList;

    @BeforeEach
    void setUp() {
        wordList = new WordList();
        wordList.setTitle("kleuren");
        ArrayList<String> colors = new ArrayList<>();
        colors.add("rood");
        colors.add("groen");
        colors.add("blauw");
        wordList.setWords(colors);
    }

    @Test
    void testGetWordListByTitle() {

        Mockito
                .when(wordListRepository.findById("kleuren"))
                .thenReturn(Optional.of(wordList));

        String expected = "kleuren";

        WordListDto found = wordListService.getWordListByTitle(wordList.getTitle());

        assertEquals(expected, found.getTitle());
    }

//    @Test
//    void nonUniqueTitleShouldReturnError() {
//        // Arrange
//        Mockito.when(wordListRepository.existsById(wordListDto.getTitle())).thenReturn(true);
//
//        // Act
//        ResponseEntity<?> responseEntity = wordListService.createWordList(wordListDto);
//
//        // Assert
//        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
//        Assertions.assertTrue(responseEntity.getBody() instanceof ErrorResponse);
//        Assertions.assertEquals(1, ((ErrorResponse) responseEntity.getBody()).getErrors().size());
//
//        Assertions.assertTrue(((((ErrorResponse) responseEntity.getBody()).getErrors()).containsKey("title")));
//        Assertions.assertSame("The title already exists.", ((ErrorResponse) responseEntity.getBody()).getErrors().get("title"));
//    }


//    @Test
//    void getAllWordLists() {
//    }
//
//
//    @Test
//    void createWordList() {
//    }
//
//    @Test
//    void deleteWordList() {
//    }
//
//    @Test
//    void updateWordList() {
//    }
}
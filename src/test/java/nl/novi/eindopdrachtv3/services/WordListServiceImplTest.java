package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.Eindopdrachtv3Application;
import nl.novi.eindopdrachtv3.dtos.UserDto;
import nl.novi.eindopdrachtv3.dtos.WordListDto;
import nl.novi.eindopdrachtv3.exceptions.BadRequestException;
import nl.novi.eindopdrachtv3.models.Exam;
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
import org.mockito.MockitoAnnotations;
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

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
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

    @Mock
    WordListDto wordListDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        wordList = new WordList();
        wordList.setTitle("kleuren");
        ArrayList<String> colors = new ArrayList<>();
        colors.add("rood");
        colors.add("groen");
        colors.add("blauw");
        wordList.setWords(colors);
        wordListDto = new WordListDto();
    }

    @Test
    void testGetWordListByTitle() {

        Mockito
                .when(wordListRepository.findById(wordList.getTitle()))
                .thenReturn(Optional.of(wordList));

        Optional<WordList> wlFound = wordListRepository.findById("kleuren");

//        WordListDto found =
        wordListService.getWordListByTitle(wordList.getTitle());

        String expected = "kleuren";

        assertEquals(expected, wlFound.get().getTitle());
    }

    @Test
    void TestGetAllWordLists() {
        wordListService.getAllWordLists();
        verify(wordListRepository).findAll();
    }

    @Test
    void nonUniqueTitleShouldReturnErrorAndNotCreateNewWordList() {
        // given
        WordListDto dto = new WordListDto(
                "dieren"
        );
        given(wordListRepository.existsById(dto.getTitle()))
                .willReturn(true);

        // when
        // then
        assertThatThrownBy(() ->wordListService.createWordList(dto))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("title" + dto.getTitle() + "taken");

        verify(wordListRepository, never()).save(any());

    }



//    @Test
//    void deleteWordList() {
//    }

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
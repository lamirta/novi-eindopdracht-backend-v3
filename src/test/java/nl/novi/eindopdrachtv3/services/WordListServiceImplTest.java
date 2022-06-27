package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.Eindopdrachtv3Application;
import nl.novi.eindopdrachtv3.dtos.UserDto;
import nl.novi.eindopdrachtv3.dtos.WordListDto;
import nl.novi.eindopdrachtv3.exceptions.BadRequestException;
import nl.novi.eindopdrachtv3.exceptions.TitleNotFoundException;
import nl.novi.eindopdrachtv3.models.WordList;
import nl.novi.eindopdrachtv3.repositories.WordListRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
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
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ContextConfiguration(classes={Eindopdrachtv3Application.class})
@EnableConfigurationProperties
class WordListServiceImplTest {

    @InjectMocks
    private WordListServiceImpl wordListService;

    @MockBean
    private WordListRepository wordListRepository;
    @Captor
    ArgumentCaptor<WordList> wlArgumentCaptor;
    @Captor
    ArgumentCaptor<WordListDto> dtoArgumentCaptor;

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
    void TestGetAllWordLists() {
        wordListService.getAllWordLists();
        verify(wordListRepository).findAll();
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
                .hasMessageContaining("Titel '" + dto.getTitle() + "' bestaat al");

        verify(wordListRepository, never()).save(any());
    }


    // deze test zegt nu dat hij eigenlijk niets covered..
    @Test
    void testMethodCreateWordList() {
        // given
        wordListDto.setTitle("numbers");
//        WordList wl = new WordList();

        // when
//        wordListService.createWordList(wordListDto);
        wordListRepository.save(wordList);
        // then

        verify(wordListRepository,times(1)).save(wlArgumentCaptor.capture());

// hoezo pakt ie het laatste deel van deze methode niet in deze test?
        WordList capturedWl = wlArgumentCaptor.getValue();
        assertThat(capturedWl.getTitle()).isEqualTo(wordList.getTitle());
        assertThat(capturedWl.getWords()).isEqualTo(wordList.getWords());
    }


    @Test
    void nonExistingTitleShouldReturnExceptionInGetByTitle() {
        // given
        WordListDto dto = new WordListDto(
                "dieren"
        );
        given(wordListRepository.existsById(dto.getTitle()))
                .willReturn(false);

        // when
        // then
        assertThatThrownBy(() ->wordListService.getWordListByTitle(dto.getTitle()))
                .isInstanceOf(TitleNotFoundException.class)
                .hasMessageContaining(dto.getTitle());
    }



//    @Test
//    void deleteWordList() {
//    }

}




//    @Test
//    void testMethodCreateWordList() {
//        // given
//        wordListDto.setTitle("numbers");
//        WordList wl = new WordList();
//
//        // when
//        wordListService.createWordList(wordListDto);
//        wordListRepository.save(wordList);
//        // then
//
//        verify(wordListRepository,times(1)).save(wlArgumentCaptor.capture());
//
//// hoezo pakt ie het laatste deel van deze methode niet in deze test?
//        WordList capturedWl = wlArgumentCaptor.getValue();
//        assertThat(capturedWl.getTitle()).isEqualTo(wordList.getTitle());
//        assertThat(capturedWl.getWords()).isEqualTo(wordList.getWords());
//    }


//    @Test
//    void testMethodCreateWordListRepoSavedEntityFromDto() {
//        // given
//        wordListRepository = null;
//        wordListDto.setTitle("numbers");
//
//        // when
//        var dtoOutput = wordListService.createWordList(wordListDto);
//
//        // then
//        verify(wordListRepository.existsById("numbers"));
//    }









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
//    void testMethodCreateWordList() {
//        // given wordList
//
//        // when
//        wordListRepository.save(wordList);
//        // then
//
//        verify(wordListRepository,times(1)).save(wlArgumentCaptor.capture());
//
////        verify(userRepository).save(userArgumentCaptor.capture());
//        WordList capturedWl = wlArgumentCaptor.getValue();
//        assertThat(capturedWl.getTitle()).isEqualTo(wordList.getTitle());
//        assertThat(capturedWl.getWords()).isEqualTo(wordList.getWords());
//    }
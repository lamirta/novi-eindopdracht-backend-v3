package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.dtos.WordListDto;
import nl.novi.eindopdrachtv3.exceptions.BadRequestException;
import nl.novi.eindopdrachtv3.exceptions.TitleNotFoundException;
import nl.novi.eindopdrachtv3.models.WordList;
import nl.novi.eindopdrachtv3.repositories.WordListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class WordListServiceImplTest {

    @Autowired
    private WordListServiceImpl wordListService;

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
    void TestGetAllWordLists() {
        wordListService.getAllWordLists();
        verify(wordListRepository).findAll();
    }

    @Test
    void testMethodGetWordListByTitle() {
        Mockito
                .when(wordListRepository.findById(wordList.getTitle()))
                .thenReturn(Optional.of(wordList));

        Optional<WordList> wlFound = wordListRepository.findById("kleuren");
        wordListService.getWordListByTitle(wordList.getTitle());

        String expected = "kleuren";

        assertEquals(expected, wlFound.get().getTitle());
    }

    @Test
    void nonUniqueTitleShouldReturnErrorAndNotCreateNewWordList() {
        WordListDto dto = new WordListDto(
                "dieren");
        given(wordListRepository.existsById(dto.getTitle()))
                .willReturn(true);

        // when // then
        assertThatThrownBy(() -> wordListService.createWordList(dto))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Titel '" + dto.getTitle() + "' bestaat al");

        verify(wordListRepository, never()).save(any());
    }

    @Test
    void testMethodCreateWordList() {
        when(wordListRepository.existsById(wordList.getTitle())).thenReturn(false);
        when(wordListRepository.save((WordList) org.mockito.Mockito.any())).thenReturn(wordList);
        WordListDto dto = new WordListDto();
        dto.setTitle(wordList.getTitle());

        assertSame(dto, wordListService.createWordList(dto));
        verify(wordListRepository).existsById(wordList.getTitle());
        verify(wordListRepository).save((WordList) org.mockito.Mockito.any());
    }

    @Test
    void nonExistingTitleShouldReturnExceptionInGetByTitle() {
        // given
        WordListDto dto = new WordListDto(
                "dieren");
        given(wordListRepository.existsById(dto.getTitle()))
                .willReturn(false);

        // when // then
        assertThatThrownBy(() -> wordListService.getWordListByTitle(dto.getTitle()))
                .isInstanceOf(TitleNotFoundException.class)
                .hasMessageContaining(dto.getTitle());
    }

}

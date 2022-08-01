package nl.novi.eindopdrachtv3.controllers;

import nl.novi.eindopdrachtv3.dtos.WordListDto;
import nl.novi.eindopdrachtv3.models.WordList;
import nl.novi.eindopdrachtv3.services.CustomUserDetailsService;
import nl.novi.eindopdrachtv3.services.JwtService;
import nl.novi.eindopdrachtv3.services.WordListServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
class WordListControllerTest {

    @MockBean
    CustomUserDetailsService customUserDetailsService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    ExamController examController;

    @MockBean
    ImageController imageController;

    @MockBean
    UserController userController;

    @MockBean
    UserProfileController upController;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WordListServiceImpl mockService;

    @Mock
    WordList wordList;

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
    }

    @Test
    void shouldReturnWordListByTitle() throws Exception {
        WordListDto dto = new WordListDto();
        dto.setTitle(wordList.getTitle());
        dto.setWords(wordList.getWords());

        when(mockService.getWordListByTitle("kleuren")).thenReturn(dto);

        mockMvc.perform(get("/wordlists/kleuren")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void shouldReturnAllWordLists() throws Exception {
        WordListDto wl = new WordListDto();
        wl.setTitle("numbers");

        List<WordListDto> allwordlists = new ArrayList<>();
        allwordlists.add(wl);


        when(mockService.getAllWordLists()).thenReturn(allwordlists);

        mockMvc.perform(get("/wordlists")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

}



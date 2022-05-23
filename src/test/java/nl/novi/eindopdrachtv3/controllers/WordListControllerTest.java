package nl.novi.eindopdrachtv3.controllers;

import nl.novi.eindopdrachtv3.Eindopdrachtv3Application;
import nl.novi.eindopdrachtv3.dtos.WordListDto;
import nl.novi.eindopdrachtv3.models.WordList;
import nl.novi.eindopdrachtv3.services.WordListService;
import nl.novi.eindopdrachtv3.services.WordListServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes={Eindopdrachtv3Application.class})
@EnableConfigurationProperties
class WordListControllerTest {

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

//    @Test
//    void shouldReturnWordListByTitle() throws Exception {
//        WordListDto dto = new WordListDto();
//
//        Mockito
//                .when(mockService.getWordListByTitle("kleuren"))
//
//                .thenReturn(
//                        dto.setTitle(wordList.getTitle()))
////                        dto.setWords(wordList.getWords())
//                );
//
//        mockMvc.perform(get("/wordlists/kleuren"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) content());
//    }


//    @Test
//    void shouldReturnAllWordLists() throws Exception {
//        WordList wl = new WordList();
//        wl.setTitle("numbers");
//
//        List<WordList> allwordlists = Arrays.asList(wl);
//
//        given(mockService.getAllWordLists()).willReturn(allwordlists);
//
//        mockMvc.perform(get("/wordlists")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$", hasSize(1)))
//                .andExpect((ResultMatcher) jsonPath("$[0].title", is(wordList.getTitle())));
//    }


}





//    @Test
//    void shouldReturnAllWordLists() throws Exception {
//        WordList wl = new WordList();
//        wl.setTitle("numbers");
//
//        List<WordList> allwordlists = Arrays.asList(wl);
//
//        given(mockService.getAllWordLists()).willReturn(allwordlists);
//
//        mockMvc.perform(get("/wordlists")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].lastName", is(customer.getTitle())));
//    }

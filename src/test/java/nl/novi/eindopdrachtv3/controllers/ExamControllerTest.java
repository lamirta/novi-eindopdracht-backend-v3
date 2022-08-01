package nl.novi.eindopdrachtv3.controllers;

import nl.novi.eindopdrachtv3.dtos.ExamDto;
import nl.novi.eindopdrachtv3.models.Exam;
import nl.novi.eindopdrachtv3.models.WordList;
import nl.novi.eindopdrachtv3.repositories.ExamRepository;
import nl.novi.eindopdrachtv3.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;


@WebMvcTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
class ExamControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomUserDetailsService customUserDetailsService;

    @MockBean
    ExamServiceImpl mockService;

    @MockBean
    ExamRepository mockRepository;

    @MockBean
    ImageController imageController;

    @MockBean
    UserController userController;

    @MockBean
    UserProfileController upController;

    @MockBean
    WordListServiceImpl wordListService;

    @MockBean
    private JwtService jwtService;

    @Mock
    WordList wordList;

    @Mock
    ExamDto examDto;

    @Mock
    Exam exam;

    @BeforeEach
    void setUp() {
        exam = new Exam();
        exam.setId(2L);
        exam.setWrongEntries(4);
        exam.setPassed(true);
    }

    @Test
    void shouldReturnExamById() throws Exception {
        ExamDto dto = new ExamDto();
        dto.setId(exam.getId());
        dto.setWrongEntries(exam.getWrongEntries());
        dto.setPassed(exam.isPassed());

        when(mockService.getExamById(2L)).thenReturn(dto);

        mockMvc.perform(get("/exams/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void shouldReturnAllExams() throws Exception {
        ExamDto dto = new ExamDto();
        dto.setId(exam.getId());

        List<ExamDto> allexams = new ArrayList<>();
        allexams.add(dto);


        when(mockService.getAllExams()).thenReturn(allexams);

        mockMvc.perform(get("/exams")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

}

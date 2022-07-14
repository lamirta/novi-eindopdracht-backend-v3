package nl.novi.eindopdrachtv3.controllers;

import nl.novi.eindopdrachtv3.dtos.ExamDto;
import nl.novi.eindopdrachtv3.models.Exam;
import nl.novi.eindopdrachtv3.models.WordList;
import nl.novi.eindopdrachtv3.repositories.ExamRepository;
import nl.novi.eindopdrachtv3.services.*;
import nl.novi.eindopdrachtv3.services.ExamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ExamController.class})
@WebMvcTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
class ExamControllerTest {

    @Autowired
    private ExamController examController;

    @MockBean
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ImageController imageController;

    @MockBean
    UserController userController;

    @MockBean
    UserProfileController upController;

    @MockBean
    ExamServiceImpl mockService;

    @MockBean
    ExamRepository mockRepository;

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
    void testGetExamById() throws Exception {
        when(this.mockService.getAllExams()).thenReturn(new ArrayList<>());
        when(this.mockService.getExamById((Long) any())).thenReturn(new ExamDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/exams/{id}", "", "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.examController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
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

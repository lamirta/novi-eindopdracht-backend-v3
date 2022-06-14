//package nl.novi.eindopdrachtv3.controllers;
//
//import nl.novi.eindopdrachtv3.Eindopdrachtv3Application;
//import nl.novi.eindopdrachtv3.dtos.ExamDto;
//import nl.novi.eindopdrachtv3.models.Exam;
//import nl.novi.eindopdrachtv3.models.WordList;
//import nl.novi.eindopdrachtv3.repositories.ExamRepository;
//import nl.novi.eindopdrachtv3.services.ExamService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest
//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc(addFilters = false)
//class ExamControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    ExamService mockService;
//
//    @MockBean
//    ExamRepository mockRepository;
//
//    @Mock
//    Exam exam;
//
//    @BeforeEach
//    void setUp() {
//        exam = new Exam();
//        exam.setId(2L);
//        exam.setWrongEntries(4);
//        exam.setPassed(true);
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//
//
//    @Test
//    void getExamById() {
//    }
//
//    @Test
//    void deleteExamById_TestSuccess() throws Exception {
//        Mockito
//                .when(mockRepository.findById(exam.getId()))
//                .thenReturn(Optional.of(exam));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .delete("exams/2")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()
//        );
//
//    }
//
//
//}
//
//
//
//
//
//
//// keep getting an error that in UserDetailsService a bean is missing..
////    @Test
////    void deleteExamById_TestSuccess() throws Exception {
////        Mockito
////                .when(mockRepository.findById(exam.getId()))
////                .thenReturn(Optional.of(exam));
////
////        mockMvc.perform(MockMvcRequestBuilders
////                .delete("exams/2")
////                .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk()
////        );
////
////    }
//
//
////    @Test
////    void getAllExams() throws Exception {
////
////        ExamDto exam = new ExamDto(1L, 3, true);
////        List<ExamDto> allExams = Arrays.asList(exam);
////
////        given(mockService.getAllExams()).willReturn(allExams);
////
////        mockMvc.perform(get("/exams")
////                        .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$", hasSize(1)));
////    }
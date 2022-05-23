package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.Eindopdrachtv3Application;
import nl.novi.eindopdrachtv3.dtos.ExamDto;
import nl.novi.eindopdrachtv3.exceptions.BadRequestException;
import nl.novi.eindopdrachtv3.models.Exam;
import nl.novi.eindopdrachtv3.models.UserProfile;
import nl.novi.eindopdrachtv3.repositories.ExamRepository;
import nl.novi.eindopdrachtv3.repositories.UserProfileRepository;
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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

//@WebMvcTest
//@ContextConfiguration(classes={Eindopdrachtv3Application.class})
//@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
class ExamServiceImplTest {

    @InjectMocks
    ExamServiceImpl mockExamService;

    @Mock
    ExamRepository mockExamRepository;

    @Mock
    UserProfileRepository userProfileRepository;

    @Mock
    Exam exam;

    @Mock
    ExamDto examDto;

    @Mock
    UserProfile userProfile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        exam = new Exam();
        exam.setId(2L);
        exam.setWrongEntries(4);
        exam.setPassed(true);
        examDto = new ExamDto();
        userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setFirstName("jan");
        userProfile.setLastName("jansen");
    }

    @Test
    void testMethodGetAllExams() {
        mockExamService.getAllExams();
        verify(mockExamRepository).findAll();
    }


    @Test
    void TestGetExamById(){

        Mockito
                .when(mockExamRepository.findById(exam.getId()))
                .thenReturn(Optional.of(exam));

        Optional<Exam> examFound = mockExamRepository.findById(2L);

//        var expected = null
        var actual = examFound.get().getId();

        // Assert / Then
        Assertions.assertEquals(2, actual);

    }

//    @Test
//    void deleteExamById_TestSuccess(){
//
//        Mockito
//                .when(mockExamRepository.findById(exam.getId()))
//                .thenReturn(Optional.of(exam));
//
//        Optional<Exam> examFound = mockExamRepository.findById(2L);
//        mockExamRepository.deleteById(examFound.get().getId());
//
////        var expected = null
//        var actual = examFound.get().getId();
//
//        // Assert / Then
//        assertNull(actual);
//
//    }

}
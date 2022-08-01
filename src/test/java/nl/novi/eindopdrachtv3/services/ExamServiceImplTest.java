package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.dtos.ExamDto;
import nl.novi.eindopdrachtv3.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtv3.models.Exam;
import nl.novi.eindopdrachtv3.models.UserProfile;
import nl.novi.eindopdrachtv3.models.WordList;
import nl.novi.eindopdrachtv3.repositories.ExamRepository;
import nl.novi.eindopdrachtv3.repositories.UserProfileRepository;
import nl.novi.eindopdrachtv3.repositories.WordListRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ExamServiceImplTest {

    @InjectMocks
    private ExamServiceImpl mockExamService;

    @MockBean
    private ExamRepository mockExamRepository;

    @Mock
    UserProfileRepository userProfileRepository;

    @Mock
    WordListRepository wlRepository;

    @Mock
    Exam exam;

    @Mock
    ExamDto examDto;

    @Mock
    UserProfile userProfile;

    @Mock
    WordList wl;

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
        wl = new WordList();
        wl.setTitle("dieren");
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

        mockExamService.getExamById(exam.getId());

        // Assert / Then
        Assertions.assertEquals(2, examFound.get().getId());
    }

    @Test
    void nonExistingExamIdShouldReturnExceptionInGetById() {
        // given
        ExamDto dto = new ExamDto(
                2L,
                3,
                true
        );
        given(mockExamRepository.existsById(dto.getId()))
                .willReturn(false);

        // when // then
        assertThatThrownBy(() ->mockExamService.getExamById(dto.getId()))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("Geen toets gevonden");
    }

    @Test
    void shouldTestMethodAssignUserProfileToExam() {
        // Arrange / Given
        UserProfile up = new UserProfile();
        up.setId(2L);

        Mockito
                .when(mockExamRepository.findById(exam.getId()))
                .thenReturn(Optional.of(exam));
        Mockito
                .when(userProfileRepository.findById(up.getId()))
                .thenReturn(Optional.of(up));

        Optional<Exam> examFound = mockExamRepository.findById(exam.getId());
        Optional<UserProfile> upFound = userProfileRepository.findById(up.getId());

        mockExamService.assignUserProfileToExam(examFound.get().getId(), upFound.get().getId());
        Exam found = examFound.get();
        Long expected = up.getId();

        // Assert / Then
        assertEquals(expected, found.getUserProfile().getId());
    }

    @Test
    void shouldTestMethodAssignWordListToExam() {
        Mockito
                .when(mockExamRepository.findById(exam.getId()))
                .thenReturn(Optional.of(exam));
        Mockito
                .when(wlRepository.findById(wl.getTitle()))
                .thenReturn(Optional.of(wl));

        Optional<Exam> examFound = mockExamRepository.findById(exam.getId());
        Optional<WordList> wlFound = wlRepository.findById(wl.getTitle());

        mockExamService.assignWordListToExam(examFound.get().getId(), wlFound.get().getTitle());
        Exam found = examFound.get();
        String expected = wl.getTitle();

        assertEquals(expected, found.getWordList().getTitle());
    }

}

package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.dtos.ExamDto;

import java.util.List;

public interface ExamService {
    List<ExamDto> getAllExams();
    ExamDto getExamById(Long id);
    ExamDto startExam(ExamDto examDto);
    void deleteExamById(Long id);
    void deleteAllExamsByUserProfile(Long id);
    void assignUserProfileToExam(Long id, Long userProfileId);
    void assignWordListToExam(Long id, String wordlistTitle);
}

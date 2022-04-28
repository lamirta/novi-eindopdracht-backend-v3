package nl.novi.eindopdrachtv3.services;

import nl.novi.eindopdrachtv3.dtos.ExamDto;

import java.util.List;

public interface ExamService {
    List<ExamDto> getExams();
    ExamDto startExam(ExamDto examDto);
    List<ExamDto> getPassedExams(boolean isPassed); // true
    List<ExamDto> getFailedExams(boolean isPassed); // false
//    List<ExamDto> getExamsOfUsername (User username);
}
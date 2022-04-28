package nl.novi.eindopdrachtv3.controllers;

import nl.novi.eindopdrachtv3.dtos.ExamDto;
import nl.novi.eindopdrachtv3.services.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ExamController {
    private final ExamService service;

    public ExamController(ExamService service) {
        this.service = service;
    }

    @GetMapping("/exams")
    public ResponseEntity<Object> getExams() {
        List<ExamDto> allExams = service.getExams();
        return new ResponseEntity<>(allExams, HttpStatus.OK);
    }

    @PostMapping("/exams")
    public ExamDto startExam(@Valid @RequestBody ExamDto examDto) {
        ExamDto exam = service.startExam(examDto);

        return exam;
    }

    @GetMapping("/exams/{isPassed}/{true}")
    public ResponseEntity<Object> getPassedExams(@PathVariable boolean isPassed) {
        List<ExamDto> allPassedExams = service.getPassedExams(true);
        return new ResponseEntity<>(allPassedExams, HttpStatus.OK);
    }


    @GetMapping("/exams/{isPassed}/{false}")
    public ResponseEntity<Object> getFailedExams(@PathVariable boolean isPassed) {
        List<ExamDto> allFailedExams = service.getFailedExams(false);
        return new ResponseEntity<>(allFailedExams, HttpStatus.OK);
    }

}
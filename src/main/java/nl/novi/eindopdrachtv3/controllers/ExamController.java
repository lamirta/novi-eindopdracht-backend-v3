package nl.novi.eindopdrachtv3.controllers;

import nl.novi.eindopdrachtv3.dtos.ExamDto;
import nl.novi.eindopdrachtv3.dtos.IdInputDto;
import nl.novi.eindopdrachtv3.services.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class ExamController {
    private final ExamService service;

    public ExamController(ExamService service) {
        this.service = service;
    }

    @GetMapping("/exams")
    public ResponseEntity<Object> getAllExams() {
        List<ExamDto> allExams = service.getAllExams();
        return new ResponseEntity<>(allExams, HttpStatus.OK);
    }

    @GetMapping("/exams/{id}")
    public ResponseEntity<ExamDto> getExamById(@PathVariable("id") Long id) {
        ExamDto edto = service.getExamById(id);

        return ResponseEntity.ok(edto);
    }

    @PostMapping("/exams")
    public ExamDto startExam(@Valid @RequestBody ExamDto examDto) {
        ExamDto exam = service.startExam(examDto);
        return exam;
    }

    @DeleteMapping("/exams/{id}")
    public void deleteExamById(@PathVariable("id") Long id) {
        service.deleteExamById(id);
    }

    // not needed in current FE, initialized with saveExam
    @PutMapping("/exams/{id}/profileId")
    public void assignUserProfileToExam(@PathVariable("id") Long id, @RequestBody IdInputDto input) {
        service.assignUserProfileToExam(id, input.id);
    }

    // not needed in current FE, initialized with saveExam
    @PutMapping("/exams/{id}/title")
    public void assignWordListToExam(@PathVariable("id") Long id, @RequestBody IdInputDto input) {
        service.assignWordListToExam(id, input.title);
    }

}

package nl.novi.eindopdrachtv3.controllers;

import nl.novi.eindopdrachtv3.dtos.ExamDto;
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

    @PutMapping("/exams/{id}/{userProfileId}")
    public void assignUserProfileToExam(@PathVariable("id") Long id, @PathVariable("userProfileId") Long userProfileId) {
        service.assignUserProfileToExam(id, userProfileId);
    }

    @PutMapping("/exams/{id}/{wordlistTitle}")
    public void assignWordListToExam(@PathVariable("id") Long id, @PathVariable("wordlistTitle") String wordlistTitle) {
        service.assignWordListToExam(id, wordlistTitle);
    }

}





//    @GetMapping("/exams/{title}")
//    public ResponseEntity<Object> getAllExamsByWordListTitle(@RequestParam String title) {
//        return null;
//    }

//    @GetMapping("/exams/{isPassed}/{true}")
//    public ResponseEntity<Object> getPassedExams(@PathVariable boolean isPassed) {
//        List<ExamDto> allPassedExams = service.getPassedExams(true);
//        return new ResponseEntity<>(allPassedExams, HttpStatus.OK);
//    }

//    // method hiervoor schrijven in service
//    @GetMapping("/exams/{isPassed}")
//    public ResponseEntity<ExamDto> getExamsByIsPassed(@PathVariable boolean isPassed) {
//        List<ExamDto> allExamResults = service.getExamsByIsPassed(isPassed);
//        return new ResponseEntity<>(allExamResults, HttpStatus.OK);
//    }
//
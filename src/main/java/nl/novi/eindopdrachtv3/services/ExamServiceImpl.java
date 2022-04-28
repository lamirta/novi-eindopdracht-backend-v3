package nl.novi.eindopdrachtv3.services;


import nl.novi.eindopdrachtv3.dtos.ExamDto;
import nl.novi.eindopdrachtv3.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtv3.exceptions.TitleNotFoundException;
import nl.novi.eindopdrachtv3.models.Exam;

import nl.novi.eindopdrachtv3.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;


    @Override
    public List<ExamDto> getExams() {
        List<Exam> el = examRepository.findAll();
        List<ExamDto> edtoList = new ArrayList<>();

        for(Exam e : el) {
            ExamDto edto = new ExamDto(e.getWrongEntries(), e.isPassed(), e.getTimestamp());
            edtoList.add(edto);
        }
        return edtoList;
    }

    @Override
    public ExamDto startExam(ExamDto examDto) {
        Exam e = new Exam();

        e.setWrongEntries(examDto.getWrongEntries());
        e.setPassed(examDto.isPassed());
        e.setTimestamp(examDto.getTimestamp());
        examRepository.save(e);

        return examDto;
    }

    // how to List of Exams with attribute boolean isPassed true??
    @Override
    public List<ExamDto> getPassedExams(boolean isPassed) {
        return null;
    }

    @Override
    public List<ExamDto> getFailedExams(boolean isPassed) {
        return null;
    }

    // wanneer relatie gelegd is.
//    @Override
//    public List<ExamDto> getExamsOfUsername(User username) {
//        return null;
//    }

    //wanneer wordlist relatie gelegd is: @GetMapping met isPassed {true} && wordlist {title} ??
}


//    @Override
//    public List<ExamDto> getPassedExams(boolean isPassed) {
//        List<ExamDto> edtoList = new ArrayList<>();
//        if (examRepository.findAllById(<isPassed>true).isPresent()) {
//            Exam e = examRepository.findById(true).get();
//            edtoList.setTitle(e.getWrongEntries());
//            edtoList.setPassed(e.isPassed());
//            edtoList.setDateTime(e.getDateTime());
//            return edtoList;
//        } else {
//            throw new RecordNotFoundException("Geen geslaagde toetsen gevonden");
//        }
//    }
package nl.novi.eindopdrachtv3.services;


import nl.novi.eindopdrachtv3.dtos.ExamDto;
import nl.novi.eindopdrachtv3.dtos.UserDto;
import nl.novi.eindopdrachtv3.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtv3.models.Exam;

import nl.novi.eindopdrachtv3.models.User;
import nl.novi.eindopdrachtv3.repositories.ExamRepository;
import nl.novi.eindopdrachtv3.repositories.UserProfileRepository;
import nl.novi.eindopdrachtv3.repositories.WordListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private WordListRepository wordListRepository;


    @Override
    public List<ExamDto> getAllExams() {
        List<Exam> el = examRepository.findAll();
        List<ExamDto> edtoList = new ArrayList<>();

        for(Exam e : el) {
            ExamDto edto = new ExamDto(
                    e.getId(),
                    e.getWrongEntries(),
                    e.isPassed(), e.getTimestamp(),
                    e.getWordList(),
                    e.getUserProfile());
            edtoList.add(edto);
        }
        return edtoList;
    }

    @Override
    public ExamDto getExamById(Long id) {
        if (examRepository.findById(id).isPresent()){
            Exam e = examRepository.findById(id).get();
            ExamDto edto = new ExamDto(e.getId(), e.getWrongEntries(), e.isPassed(), e.getTimestamp(), e.getWordList(), e.getUserProfile());
            return edto;
        } else {
            throw new RecordNotFoundException("Geen toets gevonden");
        }
    }

    @Override
    public ExamDto startExam(ExamDto examDto) {
        Exam e = new Exam();

        e.setId(examDto.getId());
        e.setWrongEntries(examDto.getWrongEntries());
        e.setPassed(examDto.isPassed());
        e.setTimestamp(examDto.getTimestamp());
        e.setWordList(examDto.getWordList());
        e.setUserProfile(examDto.getUserProfile());
        examRepository.save(e);

        return examDto;
    }

    @Override
    public void deleteExamById(Long id) throws RecordNotFoundException {
        if(examRepository.findById(id).isEmpty()) {
            throw new RecordNotFoundException("exam with id: " + id + " not present");
        }
        examRepository.deleteById(id);
    }


    @Override
    public void deleteAllExamsByUserProfile(Long id) {
        if(userProfileRepository.findById(id).isEmpty()) {
            throw new RecordNotFoundException("no userprofile with id: " + id + " present");
        }

        List<Exam> examList = examRepository.getByUserProfile(userProfileRepository.getById(id));
        examRepository.deleteAll(examList);
    }




    @Override
    public void assignUserProfileToExam(Long id, Long userProfileId) {
        var optionalExam = examRepository.findById(id);
        var optionalUserProfile = userProfileRepository.findById(userProfileId);

        if(optionalExam.isPresent() && optionalUserProfile.isPresent()) {
            var exam = optionalExam.get();
            var userProfile = optionalUserProfile.get();

            exam.setUserProfile(userProfile);
            examRepository.save(exam);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void assignWordListToExam(Long id, String wordlistTitle) {
        var optionalExam = examRepository.findById(id);
        var optionalWordList = wordListRepository.findById(wordlistTitle);

        if(optionalExam.isPresent() && optionalWordList.isPresent()) {
            var exam = optionalExam.get();
            var wordList = optionalWordList.get();

            exam.setWordList(wordList);
            examRepository.save(exam);
        } else {
            throw new RecordNotFoundException();
        }
    }

}

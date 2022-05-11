package nl.novi.eindopdrachtv3.repositories;

import nl.novi.eindopdrachtv3.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
//    List<Exam> findExamByIsPassed();

}

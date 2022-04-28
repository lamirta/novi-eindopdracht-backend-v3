package nl.novi.eindopdrachtv3.repositories;

import nl.novi.eindopdrachtv3.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
//    Exam findExamByPassedIsTrue();

}

package nl.novi.eindopdrachtv3.dtos;

import lombok.*;
import nl.novi.eindopdrachtv3.models.Exam;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class WordListDto {

    @Column(nullable = false, unique = true)
    private String title;

    @ElementCollection
    @Column(nullable = false)
    private List<String> words = new ArrayList<>();

    private List<Exam> exams;


}

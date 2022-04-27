package nl.novi.eindopdrachtv3.dtos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class WordListDto {

    // // min=2, max=128, message="Salary must mbe equal or less than 40000"
    // kan bij password proberen straks..
    @Column(nullable = false, unique = true)
    private String title;

    @ElementCollection
    @Column(nullable = false)
    private List<String> words = new ArrayList<String>();

    //    //OneToMany relation
//    private List<Exam> exams;

}

package nl.novi.eindopdrachtv3.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nl.novi.eindopdrachtv3.models.Exam;

import javax.persistence.OneToMany;
import java.util.List;

public class UserDataDto {

    Long id;

    private String firstName;
    private String lastName;
    private int age;
    private String school;

    // @OneToOne
    // private Image profilePic;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Exam> exams;

}

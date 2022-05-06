package nl.novi.eindopdrachtv3.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nl.novi.eindopdrachtv3.models.Exam;
import nl.novi.eindopdrachtv3.models.Image;
import nl.novi.eindopdrachtv3.models.User;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

public class UserDataDto {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private int age;
    private String school;

    private User username;
    private Image profilePic;
    private List<Exam> exams;



}

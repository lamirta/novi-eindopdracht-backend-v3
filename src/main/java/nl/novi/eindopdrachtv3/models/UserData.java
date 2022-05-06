package nl.novi.eindopdrachtv3.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "userData")
public class UserData {
    @Id
    @GeneratedValue
    Long id;

    private String firstName;
    private String lastName;
    private int age;
    private String school;

//    @OneToOne(mappedBy = "image")
//    private Image profilePic;

    @OneToOne(mappedBy = "userData")
    private User username;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Exam> exams;

}

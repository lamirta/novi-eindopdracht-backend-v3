package nl.novi.eindopdrachtv3.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "userData")
public class UserData {
// wil ik deze klas niet overerven van User?? ik wil hier ook een username namelijk.
    @Id
    @GeneratedValue
    Long id;

    private String firstName;
    private String lastName;
    private int age;
    private String school;

    // @OneToOne (mapperby ?? image?)
    // private Image profilePic;

    // @OneToOne (mapperby ?? user?)
    // private User user;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Exam> exams;

}

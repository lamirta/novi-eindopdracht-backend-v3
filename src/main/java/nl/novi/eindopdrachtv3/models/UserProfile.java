package nl.novi.eindopdrachtv3.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "userProfile")
public class UserProfile {
    @Id
    @GeneratedValue
    Long id;

    private String firstName;
    private String lastName;
    private int age;
    private String school;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="username", referencedColumnName = "username")
    private User username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_pic_id", referencedColumnName = "id")
    private Image profilePic;

    @OneToMany(mappedBy = "userProfile")
    @JsonIgnore
    private List<Exam> exams;

    public UserProfile() {
    }

    public UserProfile(Long id, String firstName, String lastName, int age, String school, User username, Image profilePic, List<Exam> exams) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.school = school;
        this.username = username;
        this.profilePic = profilePic;
        this.exams = exams;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }

    public User getUsername() {
        return username;
    }
    public void setUsername(User username) {
        this.username = username;
    }

    public Image getProfilePic() {
        return profilePic;
    }
    public void setProfilePic(Image profilePic) {
        this.profilePic = profilePic;
    }

    public List<Exam> getExams() {
        return exams;
    }
    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}

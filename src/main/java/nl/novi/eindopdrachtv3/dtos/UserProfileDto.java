package nl.novi.eindopdrachtv3.dtos;

import nl.novi.eindopdrachtv3.models.Exam;
import nl.novi.eindopdrachtv3.models.Image;
import nl.novi.eindopdrachtv3.models.User;

import java.util.List;

public class UserProfileDto {

    private Long id;

    private String firstName;
    private String lastName;
    private int age;
    private String school;

    private User username;
    private Image profilePic;
    private List<Exam> exams;

    public UserProfileDto() {
    }

    public UserProfileDto(Long id, String firstName, String lastName, int age, String school, User username, Image profilePic, List<Exam> exams) {
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

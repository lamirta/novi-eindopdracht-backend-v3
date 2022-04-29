package nl.novi.eindopdrachtv3.models;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wordlists")
public class WordList {

    @Id
    private String title;

    @ElementCollection
    private List<String> words = new ArrayList<>();

    @OneToMany(mappedBy = "wordList")
    @JsonIgnore  // om recursie (endless loop) te voorkomen, maar moet wel andere methode maken nu om de exams bij wordlist op te vragen.
    private List<Exam> exams;


    public WordList() {
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getWords() {
        return words;
    }
    public void setWords(List<String> words) {
        this.words = words;
    }

    public List<Exam> getExams() {
        return exams;
    }
    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}

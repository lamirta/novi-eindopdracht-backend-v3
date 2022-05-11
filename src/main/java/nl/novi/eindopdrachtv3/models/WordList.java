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
    @JsonIgnore
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

package nl.novi.eindopdrachtv3.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue
    Long id;

    private int wrongEntries;
    private Boolean isPassed;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "wordlist_title")
    private WordList wordList;

    @ManyToOne
    @JoinColumn(name = "userprofile_id")
    private UserProfile userProfile;


    public Exam() {
    }

    public Exam(Long id, int wrongEntries, Boolean isPassed) {
        this.id = id;
        this.wrongEntries = wrongEntries;
        this.isPassed = isPassed;
    }

    public Exam(Long id, int wrongEntries, Boolean isPassed, LocalDateTime timestamp, WordList wordList, UserProfile userProfile) {
        this.id = id;
        this.wrongEntries = wrongEntries;
        this.isPassed = isPassed;
        this.timestamp = timestamp;
        this.wordList = wordList;
        this.userProfile = userProfile;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public int getWrongEntries() {
        return wrongEntries;
    }
    public void setWrongEntries(int wrongEntries) {
        this.wrongEntries = wrongEntries;
    }

    public Boolean isPassed() {
        return isPassed;
    }
    public void setPassed(Boolean passed) {
        isPassed = passed;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public WordList getWordList() {
        return wordList;
    }
    public void setWordList(WordList wordList) {
        this.wordList = wordList;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}


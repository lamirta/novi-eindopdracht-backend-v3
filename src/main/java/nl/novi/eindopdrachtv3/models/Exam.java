package nl.novi.eindopdrachtv3.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue
    Long id;

    private int wrongEntries;

    private boolean isPassed;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "wordlist_title")
    private WordList wordList;

    @ManyToOne
    @JoinColumn(name = "userdata_id")
    private UserData user;


    public Exam() {
    }

    public Exam(int wrongEntries, boolean isPassed, LocalDateTime timestamp, WordList wordList, UserData user) {
        this.id = id;
        this.wrongEntries = wrongEntries;
        this.isPassed = isPassed;
        this.timestamp = timestamp;
        this.wordList = wordList;
        this.user = user;
//        this.timestamp = LocalDateTime.now();
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

    public boolean isPassed() {
        return isPassed;
    }
    public void setIsPassed(boolean isPassed) {
        this.isPassed = isPassed;
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

    public UserData getUser() {
        return user;
    }
    public void setUser(UserData user) {
        this.user = user;
    }
}


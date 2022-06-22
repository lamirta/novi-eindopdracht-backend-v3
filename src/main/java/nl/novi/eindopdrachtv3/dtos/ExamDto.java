package nl.novi.eindopdrachtv3.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import nl.novi.eindopdrachtv3.models.UserProfile;
import nl.novi.eindopdrachtv3.models.WordList;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

// how to timestemp to set on date made..

public class ExamDto {

    private Long id;
    private int wrongEntries;

    @NotNull
    private Boolean isPassed;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
    private LocalDateTime timestamp;


    private WordList wordList;
    private UserProfile userProfile;

    public ExamDto() {
    }

    public ExamDto(Long id, int wrongEntries, Boolean isPassed) {
        this.id = id;
        this.wrongEntries = wrongEntries;
        this.isPassed = isPassed;
    }

    public ExamDto(Long id, int wrongEntries, Boolean isPassed, LocalDateTime timestamp, WordList wordList, UserProfile userProfile) {
        this.id = id;
        this.wrongEntries = wrongEntries;
        this.isPassed = isPassed;
        this.timestamp = LocalDateTime.now();
        this.timestamp = LocalDateTime.now();
        this.wordList = wordList;
        this.userProfile = userProfile;
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
        this.timestamp = LocalDateTime.now();
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

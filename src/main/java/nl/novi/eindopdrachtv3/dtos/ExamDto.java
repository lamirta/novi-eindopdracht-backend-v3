package nl.novi.eindopdrachtv3.dtos;

import nl.novi.eindopdrachtv3.models.UserData;
import nl.novi.eindopdrachtv3.models.WordList;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class ExamDto {

    private Long id;

    private int wrongEntries;

    private boolean isPassed;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "wordlist_title")
    private WordList wordList;

    @ManyToOne
    @JoinColumn(name = "userdata_id")
    private UserData user;

    public ExamDto() {
    }

    public ExamDto(Long id, int wrongEntries, boolean isPassed, LocalDateTime timestamp, WordList wordList, UserData user) {
        this.id = id;
        this.wrongEntries = wrongEntries;
        this.isPassed = isPassed;
        this.timestamp = LocalDateTime.now();
        this.wordList = wordList;
        this.user = user;
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
        this.timestamp = LocalDateTime.now();
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

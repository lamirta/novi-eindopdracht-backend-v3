package nl.novi.eindopdrachtv3.models;

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

    private LocalDateTime dateTime;

//    @ManyToOne
//    @JoinColumn(name = "wordlist_id")
//    private WordList wordList;
//    // in service function schrijven:
//    //    void assignWordListToExam(Long id, String wordListTitle);

    public Exam() {
    }

    public Exam(int wrongEntries, boolean isPassed, LocalDateTime dateTime) {
        this.wrongEntries = wrongEntries;
        this.isPassed = isPassed;
        this.dateTime = LocalDateTime.now();
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
    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = LocalDateTime.now();
    }

}

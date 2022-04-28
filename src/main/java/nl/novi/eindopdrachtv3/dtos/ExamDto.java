package nl.novi.eindopdrachtv3.dtos;

import java.time.LocalDateTime;

public class ExamDto {

    private int wrongEntries;

    private boolean isPassed;

    private LocalDateTime timestamp;

    public ExamDto() {
    }

    public ExamDto(int wrongEntries, boolean isPassed, LocalDateTime dateTime) {
        this.wrongEntries = wrongEntries;
        this.isPassed = isPassed;
        this.timestamp = LocalDateTime.now();
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
    public void setPassed(boolean isPassed) {
        this.isPassed = isPassed;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = LocalDateTime.now();
    }
}

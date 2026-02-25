package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs within a specific time frame,
 * defined by a start time and an end time.
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start= start;
        this.end= end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    @Override
    public String getTaskIcon() {
        return "E";
    }

    @Override
    public String toString() {
        String formattedStart = start.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedEnd = end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return super.toString() + String.format(" (from: %s to: %s) ", formattedStart, formattedEnd);
    }
}

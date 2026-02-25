package task;

/**
 * Represents a generic task with a description and a completion status.
 * Serves as the base class for specific task types like Todo, Deadline, and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markTask(boolean isDone) {
        this.isDone = isDone;
    }

    public String getTaskIcon() {
        return " ";
    }

    public String toString() {
        return "[" + this.getTaskIcon() + "][" + this.getStatusIcon() + "] " + this.description;
    }
}

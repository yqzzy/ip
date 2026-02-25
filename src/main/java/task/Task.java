package task;

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
    //...
}

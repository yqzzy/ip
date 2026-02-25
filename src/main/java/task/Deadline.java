package task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String getTaskIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", by);
    }
}

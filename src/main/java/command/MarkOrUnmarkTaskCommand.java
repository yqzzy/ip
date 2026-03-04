package command;

import exception.ChatException;
import task.Task;
import task.TaskList;
import ui.Ui;
import storage.Storage;

public class MarkOrUnmarkTaskCommand extends Command {
    private int index;
    private Boolean isMarked;

    public MarkOrUnmarkTaskCommand(int index, Boolean isMarked) {
        this.index = index;
        this.isMarked = isMarked;
    }

    /**
     * A command that marks or unmarks a task from the task list using a specific index provided by the user.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (index < 0 || index > taskList.getSize()-1) {
                throw new ChatException("Number must be less than or equal to " + taskList.getSize());
            }
            Task task = taskList.getTask(index);
            task.markTask(isMarked);
            if (isMarked) {
                ui.showMessage("Nice, I've marked this task done:");
            } else {
                ui.showMessage("Not nice, I've unmarked this task:");
            }
            ui.showMessage(String.valueOf(task));
        } catch (ChatException e) {
            ui.showError(e.getMessage());
        }
    }
}

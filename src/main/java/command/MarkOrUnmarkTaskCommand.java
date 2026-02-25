package command;

import exception.ChatException;
import task.Task;
import task.TaskList;
import ui.Ui;
import storage.Storage;

public class MarkOrUnmarkTaskCommand extends Command {
    private String description;
    private boolean isMarked;

    public MarkOrUnmarkTaskCommand(String description, boolean isMarked) {
        this.description = description;
        this.isMarked = isMarked;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatException {
        Task task = taskList.findTaskByDescription(description);
        if (task == null) {
            throw new ChatException("task.Task not found. Enter \"list\" to see list of tasks.");
        }

        task.markTask(isMarked);
        if (isMarked) {
            ui.showMessage("Nice, I've marked this task done:");
        } else {
            ui.showMessage("Not nice, I've unmarked this task:");
        }
        ui.showMessage(String.valueOf(task));
    }
}

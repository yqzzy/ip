package command;

import exception.ChatException;
import task.Task;
import task.TaskList;
import ui.Ui;
import storage.Storage;

public class DeleteTaskCommand extends Command {
    private int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    /**
     * A command that removes a task from the task list using a specific index provided by the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (index < 0 || index > taskList.getSize()-1) {
                throw new ChatException("Number must be less than or equal to " + taskList.getSize());
            }
            Task removedTask = taskList.deleteTask(index);
            ui.showMessage("Okay, this task is removed: ");
            ui.showMessage(String.valueOf(removedTask));
            ui.showMessage("Now you have " + taskList.getSize() + " tasks in the list.");
        } catch (NumberFormatException e) {
            ui.showError("Error: Enter index of the task to delete");
        } catch (ChatException e) {
            ui.showError(e.getMessage());
        }
    }
}

package command;

import exception.ChatException;
import task.Task;
import task.TaskList;
import ui.Ui;
import storage.Storage;

public class DeleteTaskCommand extends Command {
    private String input;

    public DeleteTaskCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String[] inputParts = input.split(" ", 2);
        try {
            int number = Integer.parseInt(inputParts[1]);
            if (number > taskList.getSize()) {
                throw new ChatException("Number must be less than or equal to " + taskList.getSize());
            }
            Task removedTask = taskList.deleteTask(number-1);
            ui.showMessage("Okay, this task is removed: ");
            ui.showMessage(String.valueOf(removedTask));
            ui.showMessage("Now you have " + taskList.getSize() + " tasks in the list.");
        } catch (NumberFormatException e) {
            ui.showError("Error: Enter number of the task to delete");
        } catch (ChatException e) {
            ui.showError(e.getMessage());
        }
    }
}

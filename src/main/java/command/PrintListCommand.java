package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class PrintListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.getSize() == 0) {
            ui.showMessage("Your task list is empty.");
            return;
        }
        for (int i = 0; i < taskList.getSize(); i++) {
            ui.showMessage((i + 1) + ". " + taskList.getTask(i));
        }
    }
}

package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

import java.io.IOException;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.showMessage("Bye. Hope to see you again soon!\n");
        storage.saveFile(taskList);
        isExit = true;
    }
}

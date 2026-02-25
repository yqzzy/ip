package command;
import exception.ChatException;
import task.TaskList;
import ui.Ui;
import storage.Storage;

import java.io.IOException;


public abstract class Command {
    // Shared state to determine if the program should end
    protected boolean isExit = false;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChatException, IOException;

    public boolean isExit() {
        return isExit;
    }
}

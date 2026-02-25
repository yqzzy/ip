package command;
import exception.ChatException;
import task.TaskList;
import ui.Ui;
import storage.Storage;

import java.io.IOException;

/**
 * Represents an abstract executable command.
 * All specific user actions (e.g., adding a task) must inherit from this class.
 */
public abstract class Command {
    // Shared state to determine if the program should end
    protected boolean isExit = false;

    /**
     * Executes the command by interacting with the task list, UI, and storage.
     * * @param taskList The list of tasks to be modified or accessed.
     * @param ui       The user interface used to display feedback.
     * @param storage  The storage handler for saving changes to disk.
     * @throws ChatException If the command logic fails (e.g., invalid task index).
     * @throws IOException   If there is an error writing to the data file.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChatException, IOException;

    public boolean isExit() {
        return isExit;
    }
}

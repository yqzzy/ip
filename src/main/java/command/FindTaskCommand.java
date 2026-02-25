package command;

import exception.ChatException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class FindTaskCommand extends Command{
    private String keyword;

    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatException, IOException {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        ui.showMessage("Here are matching tasks in your list: ");
        for (int i = 0; i < matchingTasks.size(); i++) {
            ui.showMessage((i + 1) + ". " + matchingTasks.get(i));
        }
    }
}

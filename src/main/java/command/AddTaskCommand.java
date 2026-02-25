package command;

import exception.ChatException;
import task.*;
import ui.Ui;
import storage.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddTaskCommand extends Command {
    private String input;

    public AddTaskCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatException {
        String[] inputParts = input.split(" ", 2);

        if (inputParts.length < 2) {
            throw new ChatException("task.Task description cannot be empty.");
        }

        String taskType = inputParts[0];
        String taskDetails = inputParts[1];

        Task newTask = null;

        switch (taskType) {
        case "todo": {
            newTask = new Todo(taskDetails);
            break;
        }
        case "deadline": {
            if (! taskDetails.contains("/by")) {
                throw new ChatException("Invalid deadline format. Use \"/by <deadline>\".");
            }
            String[] subParts = taskDetails.split(" /by ", 2);
            String description = subParts[0];
            String by = subParts[1];
            newTask = new Deadline(description, by);
            break;
        }
        case "event": {
            if (!(taskDetails.contains("/from") && taskDetails.contains("/to"))) {
                throw new ChatException("Invalid event format. Use \"/from <start date> /to <end date>\".");
            }
            String[] subParts = taskDetails.split(" /from ", 2);
            String description = subParts[0];
            String[] subSubParts = subParts[1].split(" /to ", 2);
            try {
                LocalDate to = LocalDate.parse(subSubParts[0]);
                LocalDate from = LocalDate.parse(subSubParts[1]);
                newTask = new Event(description, to, from);
            } catch (DateTimeParseException e) {
                throw new ChatException("Dates must be in YYYY-MM-DD format (e.g., 2026-02-25).");
            }
            break;
        }
        }

        taskList.addTask(newTask);
        ui.showMessage("Added: " + newTask);
        ui.showMessage(String.format("Now you have %d tasks in the list%n", taskList.getSize()));
    }

}


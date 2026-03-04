package parser;

import command.*;
import exception.ChatException;

/**
 * Processes user input strings and converts them into executable Command objects.
 * Handles the logic for identifying command types and extracting arguments.
 */
public class Parser {
    public Command parse(String input) throws ChatException {

        if (input.equals("bye")) {
            return new ExitCommand();
        }
        else if (input.equals("list")) {
            return new PrintListCommand();
        }
        else if (input.startsWith("mark ")) {
            int index = getIndex(input);
            return new MarkOrUnmarkTaskCommand(index, true);
        }
        else if (input.startsWith("unmark ")) {
            int index = getIndex(input);
            return new MarkOrUnmarkTaskCommand(index, false);
        }
        else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")){
            return new AddTaskCommand(input);
        }
        else if (input.startsWith("delete")) {
            int index = getIndex(input);
            return new DeleteTaskCommand(index);
        }
        else if (input.startsWith("find")) {
            return new FindTaskCommand(input.substring(5));
        }
        else {
            throw new ChatException("Invalid command. Valid command starts with: bye, list, mark, unmark, todo, deadline, event, delete, find");
        }
    }

    private int getIndex(String input) throws ChatException {
        String[] inputParts = input.split(" ", 2);
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new ChatException("Please specify a task number (e.g., mark 1).");
        }
        try {
            return Integer.parseInt(inputParts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new ChatException("Enter index of the task to mark or unmark");
        }
    }

}

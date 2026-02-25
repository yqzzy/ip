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
            return new MarkOrUnmarkTaskCommand(input.substring(5), true);
        }
        else if (input.startsWith("unmark ")) {
            return new MarkOrUnmarkTaskCommand(input.substring(7), false);
        }
        else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")){
            return new AddTaskCommand(input);
        }
        else if (input.startsWith("delete")) {
            return new DeleteTaskCommand(input);
        }
        else if (input.startsWith("find")) {
            return new FindTaskCommand(input.substring(5));
        }
        else {
            throw new ChatException("Invalid command. Valid command starts with: bye, list, mark, unmark, todo, deadline, event, delete, find");
        }
    }

}

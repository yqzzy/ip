import command.Command;
import exception.ChatException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Chat {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Chat(String filePath) throws ChatException, FileNotFoundException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadFile());
        parser = new Parser();
    }

    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ChatException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws ChatException, FileNotFoundException {
        new Chat("data/tasks.txt").run();
    }
}
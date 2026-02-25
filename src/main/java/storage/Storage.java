package storage;

import exception.ChatException;
import task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all file I/O operations for the application.
 * Responsible for loading task data from disk and saving current tasks to a text file.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        ensurePathExists();
    }

    private void ensurePathExists() {
        try {
            File file = new File(filePath);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error handling file.");
        }
    }

    /**
     * Parses a raw line from the save file into a Task object.
     * * @param taskParts The array of strings split from the file line.
     * @param taskType The encoded type of the task (T, D, or E).
     * @return The specific Task object (Todo, Deadline, or Event).
     * @throws ChatException If the task type is unrecognized or data is missing.
     */
    private Task getTask(String[] taskParts, String taskType) throws ChatException {
        String taskStatus = taskParts[1];
        String taskDescription = taskParts[2];
        Task task;
        switch (taskType) {
        case "D": {
            String by = taskParts[3];
            task = new Deadline(taskDescription, by);
            break;
        }
        case "E": {
            LocalDate from = LocalDate.parse(taskParts[3]);
            LocalDate to = LocalDate.parse(taskParts[4]);
            task = new Event(taskDescription, from, to);
            break;
        }
        case "T": {
            task = new Todo(taskDescription);
            break;
        }
        default:
            throw new ChatException("Invalid task type");
        }
        task.markTask((taskStatus).equals("X"));
        return task;
    }

    public void saveFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < taskList.getSize(); i++) {
            Task currTask = taskList.getTask(i);
            String textToAdd = String.format("%s | %s | %s", currTask.getTaskIcon(), currTask.getStatusIcon(), currTask.getDescription());
            if (currTask instanceof Deadline) {
                textToAdd += String.format(" | %s", ((Deadline) currTask).getBy());
            } else if (currTask instanceof Event) {
                textToAdd += String.format(" | %s | %s", ((Event) currTask).getStart(), ((Event) currTask).getEnd());
            }
            fw.write(textToAdd + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Loads task data from the hard disk.
     * Parses the text file and converts each line into the appropriate Task object.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws FileNotFoundException If the data file does not exist.
     * @throws ChatException If the file format is corrupted or invalid.
     */
    public ArrayList<Task> loadFile() throws FileNotFoundException, ChatException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<>();
        while (s.hasNext()) {
            String[] taskParts = s.nextLine().split(" \\| ");
            String taskType = taskParts[0];
            Task task = getTask(taskParts, taskType);
            taskList.add(task);
        }
        s.close();
        System.out.println("File loaded successfully.");
        return taskList;
    }
}

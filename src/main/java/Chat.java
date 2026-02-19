import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Chat {
    private static final String DIVIDER = "----------------------------------------";
    private static final String LOGO = """
             CCCCC  H   H   AAAA   TTTTT
            C       H   H  A    A    T
            C       HHHHH  AAAAAA    T
            C       H   H  A    A    T
             CCCCC  H   H  A    A    T""";
    private static boolean isActive = true;

    public static void main(String[] args) throws ChatException, IOException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        Scanner scanner = new Scanner(System.in);

        printWelcome();
        loadFile(taskList);

        while (isActive) {
            try {
                String input = scanner.nextLine();
                handleCommand(input, taskList);
            } catch (ChatException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println(DIVIDER);
        }

        scanner.close();
        saveFile(taskList);
    }

    private static void printWelcome() {
        System.out.println("Hello I'm \n" + LOGO);
        System.out.println("What can I do for you?\n");
    }

    private static void handleCommand(String input, ArrayList<Task> taskList) throws ChatException {

        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!\n");
            isActive = false;
        }
        else if (input.equals("list")) {
            printList(taskList);
        }
        else if (input.startsWith("mark ")) {
            markOrUnmarkTask(taskList, input.substring(5), true);
        }
        else if (input.startsWith("unmark ")) {
            markOrUnmarkTask(taskList, input.substring(7), false);
        }
        else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")){
            createAndAddTask(input, taskList);
        }
        else if (input.startsWith("delete")) {
            deleteTask(taskList, input);
        }
        else {
            throw new ChatException("Invalid command. Valid command starts with: bye, list, mark, unmark, todo, deadline, event");
        }

    }

    private static void printList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("Your task list is empty.");
            return;
        }
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

    private static Task findTaskByDescription(ArrayList<Task> taskList, String description) {
        for (Task task : taskList) {
            if (task.getDescription().equals(description)) {
                return task;
            }
        }
        return null;
    }

    private static void markOrUnmarkTask(ArrayList<Task> taskList, String description, boolean isMarked) throws ChatException {
        Task task = findTaskByDescription(taskList, description);
        if (task == null) {
            throw new ChatException("Task not found. Enter \"list\" to see list of tasks.");
        }

        task.markTask(isMarked);
        if (isMarked) {
            System.out.println("Nice, I've marked this task done:");
        } else {
            System.out.println("Not nice, I've unmarked this task:");
        }
        System.out.println(task);
    }

    private static void createAndAddTask(String input, ArrayList<Task> taskList) throws ChatException {
        String[] inputParts = input.split(" ", 2);

        if (inputParts.length < 2) {
            throw new ChatException("Task description cannot be empty.");
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
            String to = subSubParts[0];
            String from = subSubParts[1];
            newTask = new Event(description, to, from);
            break;
        }
        }

        taskList.add(newTask);
        System.out.println("Added: " + newTask);
        System.out.printf("Now you have %d tasks in the list%n", taskList.size());
    }

    private static void deleteTask(ArrayList<Task> taskList, String input) throws ChatException {
        String[] inputParts = input.split(" ", 2);
        try {
            int number = Integer.parseInt(inputParts[1]);
            if (number > taskList.size()) {
                throw new ChatException("Number must be less than or equal to " + taskList.size());
            }
            Task removedTask = taskList.remove(number);
            System.out.println("Okay, this task is removed: ");
            System.out.println(removedTask);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Enter number of the task to delete");
        }
    }

    private static void ensurePathExists(String filePath ) {
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

    private static void saveFile(ArrayList<Task> taskList) throws IOException {
        String filePath = "./data/chat.txt";
        ensurePathExists(filePath);
        FileWriter fw = new FileWriter(filePath);
        for (Task currTask : taskList) {
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

    private static void loadFile(ArrayList<Task> taskList) throws FileNotFoundException, ChatException {
        String filePath = "./data/chat.txt";
        ensurePathExists(filePath);
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] taskParts = s.nextLine().split(" \\| ");
            String taskType = taskParts[0];
            Task task = getTask(taskParts, taskType);
            taskList.add(task);
        }
        s.close();
        System.out.println("File loaded: ");
        printList(taskList);
    }

    private static Task getTask(String[] taskParts, String taskType) throws ChatException {
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
            String from = taskParts[3];
            String to = taskParts[4];
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
}
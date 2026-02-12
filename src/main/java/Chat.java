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

    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<Task>();
        Scanner scanner = new Scanner(System.in);

        printWelcome();

        while (isActive) {
            String input =  scanner.nextLine();
            handleCommand(input, taskList);
            System.out.println(DIVIDER);
        }

        scanner.close();
    }

    private static void printWelcome() {
        System.out.println("Hello I'm \n" + LOGO);
        System.out.println("What can I do for you?\n");
    }

    private static void handleCommand(String input, ArrayList<Task> taskList) {
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!\n");
            isActive = false;
        }
        else if (input.equals("list")) {
            printList(taskList);
        }
        else if (input.startsWith("mark ")) {
            markOrUnmarkTask();Task(taskList, input.substring(5), true);
        }
        else if (input.startsWith("unmark ")) {
            markOrUnmarkTask(taskList, input.substring(7), false);
        }
        else {
            createAndAddTask(input, taskList);
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

    private static void markOrUnmarkTask(ArrayList<Task> taskList, String description, boolean isMarked) {
        Task task = findTaskByDescription(taskList, description);
        if (task == null) {
            System.out.println("Task not found.");
            return;
        }

        task.markTask(isMarked);
        if (isMarked) {
            System.out.println("Nice, I've marked this task done:");
        } else {
            System.out.println("Not nice, I've unmarked this task:");
        }
        System.out.println(task);
    }

    private static void createAndAddTask(String input, ArrayList<Task> taskList) {
        String[] inputParts = input.split(" ", 2);

        if (inputParts.length < 2) {
            System.out.println("Invalid add task format.");
            return;
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
            String[] subParts = taskDetails.split(" /by ", 2);
            String description = subParts[0];
            String by = subParts[1];
            newTask = new Deadline(description, by);
            break;
        }
        case "event": {
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
}
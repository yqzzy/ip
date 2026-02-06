import java.util.ArrayList;
import java.util.Scanner;

public class Chat {
    public static void main(String[] args) {
        String logo = """
                 CCCCC  H   H   AAAA   TTTTT
                C       H   H  A    A    T \s
                C       HHHHH  AAAAAA    T \s
                C       H   H  A    A    T \s
                 CCCCC  H   H  A    A    T \s""";
        System.out.println("Hello I'm \n" + logo);
        System.out.println("What can I do for you?\n");
        ArrayList<Task> toDoList = new ArrayList<Task>();
        int size = 0;
        String input;
        Scanner in = new Scanner(System.in);

        while (true) {
            input =  in.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            }
            else if (input.equals("list")) {
                for (int i = 0; i < toDoList.size(); i++) {
                    Task currTask = toDoList.get(i);
                    System.out.println(i + 1 + ". " + currTask);
                }
            }
            else if (input.contains("unmark ")) {
                String taskDesc = input.substring(input.indexOf("unmark") + 7);
                for (Task currTask : toDoList) {
                    if (currTask.description.equals(taskDesc)) {
                        currTask.markTask(false);
                        System.out.println("Not nice, I've unmarked this task: ");
                        System.out.println(currTask);
                        break;
                    }
                }
            }
            else if (input.contains("mark ")) {
                String taskDesc = input.substring(input.indexOf("mark")+5);
                for (Task currTask : toDoList) {
                    if (currTask.description.equals(taskDesc)) {
                        currTask.markTask(true);
                        System.out.println("Nice, I've marked this task done: ");
                        System.out.println(currTask);
                        break;
                    }
                }
            }
            else {
                Task newTask = new Task("");
                String[] parts = input.split(" ", 2);
                String taskType = parts[0];
                String taskDetails = parts[1];

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

                toDoList.add(newTask);
                size ++;
                System.out.println("Added: " + newTask);
                System.out.println(String.format("Now you have %d tasks in the list", size));
            }
            System.out.println("----------------------------------------");
        }
    }
}
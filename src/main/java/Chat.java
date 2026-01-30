import java.util.ArrayList;
import java.util.Scanner;

public class Chat {
    public static void main(String[] args) {
        String logo = " CCCCC  H   H   AAAA   TTTTT\n" +
                "C       H   H  A    A    T  \n" +
                "C       HHHHH  AAAAAA    T  \n" +
                "C       H   H  A    A    T  \n" +
                " CCCCC  H   H  A    A    T  ";
        System.out.println("Hello I'm \n" + logo);
        System.out.println("What can I do for you?\n");
<<<<<<< HEAD
        ArrayList<Task> toDoList = new ArrayList<Task>();
        int size = 0;
=======
        ArrayList<String> toDoList = new ArrayList<String>();
>>>>>>> 69c778e6c52ffc5df1773b8b5413ea9960ef0d4a
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
<<<<<<< HEAD
                    System.out.println(Integer.toString(i+1) + ". [" + toDoList.get(i).getStatusIcon() + "] " + toDoList.get(i).description);
                }
            }
            else if (input.contains("unmark ")) {
                String taskDesc = input.substring(input.indexOf("unmark") + 7);
                for (Task currTask : toDoList) {
                    if (currTask.description.equals(taskDesc)) {
                        currTask.markTask(false);
                        System.out.println("Not nice, I've unmarked this task: ");
                        System.out.println("[ ] " + currTask.description);
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
                        System.out.println("[X] " + currTask.description);
                        break;
                    }
                }
            }
            else {
                Task newTask = new Task(input);
                toDoList.add(newTask);
                size ++;
=======
                    System.out.println(Integer.toString(i+1) + ". " + toDoList.get(i));
                }
            }
            else {
                toDoList.add(input);
>>>>>>> 69c778e6c52ffc5df1773b8b5413ea9960ef0d4a
                System.out.println("Added: " + input);
            }
            System.out.println("----------------------------------------");
        }
    }
}

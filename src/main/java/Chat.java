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
        ArrayList<String> toDoList = new ArrayList<String>();
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
                    System.out.println(Integer.toString(i+1) + ". " + toDoList.get(i));
                }
            }
            else {
                toDoList.add(input);
                System.out.println("Added: " + input);
            }
            System.out.println("----------------------------------------");
        }
    }
}

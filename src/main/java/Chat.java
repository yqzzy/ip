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
        String input;
        Scanner in = new Scanner(System.in);
        do {
            input =  in.nextLine();
            System.out.println(input);
            System.out.println("----------------------------------------");
        } while (!"bye".equals(input));
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}

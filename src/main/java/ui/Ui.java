package ui;

import java.util.Scanner;

/**
 * Handles all interactions with the user.
 * Responsible for reading input from the console and displaying
 * messages, errors, and formatting (like dividers) to the user.
 */
public class Ui {
    private static final String DIVIDER = "----------------------------------------";
    private static final String LOGO = """
             CCCCC  H   H   AAAA   TTTTT
            C       H   H  A    A    T
            C       HHHHH  AAAAAA    T
            C       H   H  A    A    T
             CCCCC  H   H  A    A    T""";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printWelcome() {
        System.out.println("Hello I'm \n" + LOGO);
        System.out.println("What can I do for you?\n");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

}

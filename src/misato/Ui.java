package misato;

import java.util.Scanner;

/**
 * Handles all user interactions, reading input, and displaying messages to the console.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String WELCOME_MESSAGE = "Hello! I'm Misato Katsuragi\nWhat can I do for you?";
    private static final String GOODBYE_MESSAGE = "Bye bye!\nFind for yourself why you came here. And...\n"
            + "when you're finished... come back.\nPromise me.\nHave a good time.";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome() {
        showLine();
        System.out.println(WELCOME_MESSAGE);
        showLine();
    }

    public void showGoodbye() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public void showError(String message) {
        System.out.println("☹ OOPS!!! " + message);
    }

    public void showLoadingError() {
        System.out.println("Error reading the data file. Starting with an empty list.");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
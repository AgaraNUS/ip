import java.util.Scanner;

public class misato {

    // Global state (static variables so all functions can access them)
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        printGreeting();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        // Main command loop
        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye bye")) {
                break;
            }

            handleCommand(userInput);
        }

        printExit();
        scanner.close();
    }

    // ---------------------------------------------------------
    // LOGIC HANDLERS
    // ---------------------------------------------------------

    // Decides which method to call based on the user's input
    private static void handleCommand(String userInput) {
        if (userInput.equalsIgnoreCase("list")) {
            listTasks();
        }
        else if (userInput.startsWith("mark")) {
            markTask(userInput);
        }
        else if (userInput.startsWith("unmark")) {
            unmarkTask(userInput);
        }
        else {
            addTask(userInput);
        }
    }

    private static void listTasks() {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
        }
        printLine();
    }

    private static void markTask(String command) {
        int index = getIndexFromCommand(command);
        tasks[index].markAsDone();

        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [" + tasks[index].getStatusIcon() + "] " + tasks[index].description);
        printLine();
    }

    private static void unmarkTask(String command) {
        int index = getIndexFromCommand(command);
        tasks[index].markAsUndone();

        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  [" + tasks[index].getStatusIcon() + "] " + tasks[index].description);
        printLine();
    }

    private static void addTask(String description) {
        tasks[taskCount] = new Task(description);
        taskCount++;

        printLine();
        System.out.println("added: " + description);
        printLine();
    }

    // ---------------------------------------------------------
    // HELPER METHODS
    // ---------------------------------------------------------

    // Extracts the number from commands like "mark 2" and converts to array index (0-based)
    private static int getIndexFromCommand(String command) {
        String[] parts = command.split(" ");
        return Integer.parseInt(parts[1]) - 1;
    }

    private static void printGreeting() {
        String name = "Misato Katsuragi";
        printLine();
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");
        printLine();
    }

    private static void printExit() {
        printLine();
        System.out.println("Bye! See you again!");
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}

// ---------------------------------------------------------
// CLASS DEFINITIONS
// ---------------------------------------------------------
class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }
}


/*public class misato {

    public static void main(String[] args) {
        //String END_LINE = "____________________________________________________________";
        String name = "Misato Katsuragi";
        printLine();
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");
        printLine();

        //Initialise scanner for user input
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        //Intialise array to store tasks
        String[] tasks = new String[100];
        int taskCount = 0;

        //Add, List loop
        while(true) {
            userInput = scanner.nextLine();

            //exit the loop
            if (userInput.equalsIgnoreCase("bye bye") ) {
                break;
            }

            //handle "list"command
            if (userInput.equalsIgnoreCase("list")) {
                printLine();
                for (int i = 0; i < taskCount; i++) {
                    //Display task list
                    System.out.println("Here are the tasks in your list: ");
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                printLine();
            }
            else {
                tasks[taskCount] = userInput;
                taskCount++;

                printLine();
                System.out.println("added: " + userInput);
                printLine();
            }
        }

        //Echo loop
        while (true) {
            userInput = scanner.nextLine();  //user will type something

            if (userInput.equalsIgnoreCase("bye bye")){

                break;  //exit loop when user types "bye bye"
            }
            //echo the command back
            echoCommand(userInput);
        }

        printLine();
        System.out.println("Bye! See you again!");
        printLine();

        scanner.close();
    }

    public static void echoCommand(String command) {
        printLine();
        System.out.println(command);
        printLine();
    }

    public static void printLine() {
        String END_LINE = "____________________________________________________________";
        System.out.println(END_LINE);
    }
}
*/



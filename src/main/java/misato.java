import java.util.Scanner;
//import java.util.ArrayList;

public class Misato {

    // Global state
   /*protected static ArrayList<Task> tasks = new ArrayList<>();*/
    protected static Task[] tasks = new Task[100];
    protected static int taskCount = 0;

    public static void main(String[] args) {
        printGreeting();

        Scanner scanner = new Scanner(System.in);
        String userInput;

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
        // New Command: "todo"
        else if (userInput.startsWith("todo")) {
            addTodo(userInput);
        }
        // New Command: "deadline"
        else if (userInput.startsWith("deadline")) {
            addDeadline(userInput);
        }
        // New Command: "event"
        else if (userInput.startsWith("event")) {
            addEvent(userInput);
        }
        else {
            // For now, if command is unknown, we can just print an error or ignore
            printLine();
            System.out.println("Unknown command!");
            printLine();
        }
    }

    private static void listTasks() {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            // The .toString() method handles the formatting specific to each class
            System.out.println((i + 1) + "." + tasks[i].toString());
        }
        printLine();
    }

    private static void markTask(String command) {
        int index = getIndexFromCommand(command);
        tasks[index].markAsDone();

        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks[index].toString());
        printLine();
    }

    private static void unmarkTask(String command) {
        int index = getIndexFromCommand(command);
        tasks[index].markAsUndone();

        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks[index].toString());
        printLine();
    }

    // ---------------------------------------------------------
    // ADD TASK HANDLERS
    // ---------------------------------------------------------

    private static void addTodo(String command) {
        // Format: "todo description"
        String description = command.substring(5).trim(); // Remove "todo "
        Task newTask = new Todo(description);
        addTask(newTask);
    }

    private static void addDeadline(String command) {
        // Format: "deadline description /by date"
        int byIndex = command.indexOf("/by");
        String description = command.substring(9, byIndex).trim(); // Remove "deadline "
        String by = command.substring(byIndex + 4).trim(); // Remove "/by "

        Task newTask = new Deadline(description, by);
        addTask(newTask);
    }

    private static void addEvent(String command) {
        // Format: "event description /from start /to end"
        int fromIndex = command.indexOf("/from");
        int toIndex = command.indexOf("/to");

        String description = command.substring(6, fromIndex).trim(); // Remove "event "
        String from = command.substring(fromIndex + 6, toIndex).trim(); // Remove "/from "
        String to = command.substring(toIndex + 4).trim(); // Remove "/to "

        Task newTask = new Event(description, from, to);
        addTask(newTask);
    }

    // Unified method to print the "Got it" message and update count
    private static void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;

        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printLine();
    }

    // ---------------------------------------------------------
    // HELPER METHODS
    // ---------------------------------------------------------

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
// CLASS DEFINITIONS (INHERITANCE)
// ---------------------------------------------------------

// Base Class
class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    // Base toString method
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

// Subclass: Todo
class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

// Subclass: Deadline
class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

// Subclass: Event
class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
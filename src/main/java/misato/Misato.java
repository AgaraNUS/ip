package misato;

import misato.exceptions.MisatoException;
import misato.tasks.Deadline;
import misato.tasks.Event;
import misato.tasks.Task;
import misato.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Misato {

    // CONSTANTS
    private static final String LINE = "____________________________________________________________";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_BYE_ALT = "bye bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";
    private static final String FILE_PATH = Paths.get(".", "data", "misato.txt").toString();
    private static final String COMMAND_SAVE = "save";

    // Tasks list
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        // NEW: Load existing tasks before starting
        loadTasks();

        printGreeting();

        Scanner scanner = new Scanner(System.in);
        String userInput;
        boolean isRunning = true;

        while (isRunning) {
            userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase(COMMAND_BYE) || userInput.equalsIgnoreCase(COMMAND_BYE_ALT)) {
                isRunning = false;
            } else {
                try {
                    handleCommand(userInput);
                } catch (MisatoException e) {
                    printError(e.getMessage());
                }
            }
        }

        printExit();
        scanner.close();
    }
    // FILE I/O HANDLERS (Level 7)

    private static void loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return; // File doesn't exist yet, start with an empty list
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                try {
                    Task task = parseLineToTask(line);
                    tasks.add(task);
                } catch (Exception e) {
                    // STRETCH GOAL: Ignore corrupted data lines silently (or print a warning)
                    System.out.println("Skipping corrupted data line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the data file.");
        }
    }

    private static void saveTasks() {
        File file = new File(FILE_PATH);
        File parentDir = file.getParentFile();

        // Handle case where directory doesn't exist
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (FileWriter fw = new FileWriter(file)) {
            for (Task task : tasks) {
                fw.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            printError("I couldn't save your tasks to the hard disk!");
        }
    }

    private static Task parseLineToTask(String line) throws Exception {
        // Regex escaping required for pipe symbol
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                task = new Deadline(description, parts[3]);
                break;
            case "E":
                task = new Event(description, parts[3], parts[4]);
                break;
            default:
                throw new Exception("Unknown task type");
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    // LOGIC HANDLERS
    private static void handleCommand(String userInput) throws MisatoException {
        if (userInput.equalsIgnoreCase(COMMAND_LIST)) {
            listTasks();
        } else if (userInput.startsWith(COMMAND_MARK)) {
            markTask(userInput, true);
        } else if (userInput.startsWith(COMMAND_UNMARK)) {
            markTask(userInput, false);
        } else if (userInput.startsWith(COMMAND_TODO)) {
            addTodo(userInput);
        } else if (userInput.startsWith(COMMAND_DEADLINE)) {
            addDeadline(userInput);
        } else if (userInput.startsWith(COMMAND_EVENT)) {
            addEvent(userInput);
        } else if (userInput.startsWith(COMMAND_DELETE)) {
            deleteTask(userInput);
        } else if (userInput.startsWith(COMMAND_FIND)) { // NEW
            findTasks(userInput);
        } else {
            throw new MisatoException("I'm sorry, but I don't know what that means. ");
        }
    }

    private static void listTasks() {
        printLine();
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }

        if (tasks.isEmpty()) {
            System.out.println("It all... returns... to nothing...");
        }
        printLine();
    }

    private static void markTask(String command, boolean isDone) throws MisatoException {
        try {
            String[] parts = command.split(" ");
            if (parts.length < 2) {
                throw new MisatoException("You didn't tell me which task to mark!");
            }

            int index = Integer.parseInt(parts[1]) - 1;

            if (index < 0 || index >= tasks.size()) {
                throw new MisatoException("Task number out of range.");
            }

            Task task = tasks.get(index);

            if (isDone) {
                task.markAsDone();
                printLine();
                System.out.println("Omedeto! OP-kun!");
            } else {
                task.markAsUndone();
                printLine();
                System.out.println("Tsk, just get on with it already");
            }
            System.out.println("  " + task.toString());
            printLine();
            saveTasks();

        } catch (NumberFormatException e) {
            throw new MisatoException("Please provide a valid task number.");
        }
    }

    private static void deleteTask(String command) throws MisatoException {
        try {
            String[] parts = command.split(" ");
            if (parts.length < 2) {
                throw new MisatoException("Which task do you want to delete?");
            }

            int index = Integer.parseInt(parts[1]) - 1;

            if (index < 0 || index >= tasks.size()) {
                throw new MisatoException("Task number is out of range.");
            }

            Task removedTask = tasks.remove(index);

            printLine();
            System.out.println("You better have a good reason for this!");
            System.out.println("  " + removedTask.toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            printLine();

            // NEW: Save after deleting
            saveTasks();

        } catch (NumberFormatException e) {
            throw new MisatoException("Enter a valid number, onegai.");
        }
    }

    // ADD TASK HANDLERS

    private static void addTodo(String command) throws MisatoException {
        if (command.length() <= COMMAND_TODO.length()) {
            throw new MisatoException("The todo command needs to be typed properly.\n");
        }

        String description = command.substring(COMMAND_TODO.length()).trim();
        if (description.isEmpty()) {
            throw new MisatoException("The description of a todo cannot be empty.\n");
        }

        addTaskToList(new Todo(description));
    }

    private static void addDeadline(String command) throws MisatoException {
        int byIndex = command.indexOf("/by");

        if (byIndex == -1) {
            throw new MisatoException("Invalid format. Use: deadline <desc> /by <date>.\n");
        }

        String description = command.substring(COMMAND_DEADLINE.length(), byIndex).trim();

        if (byIndex + 3 >= command.length()) {
            throw new MisatoException("You forgot to say when the deadline is!");
        }

        String by = command.substring(byIndex + 3).trim();

        if (description.isEmpty() || by.isEmpty()) {
            throw new MisatoException("The description or date cannot be empty.");
        }

        addTaskToList(new Deadline(description, by));
    }

    private static void addEvent(String command) throws MisatoException {
        int fromIndex = command.indexOf("/from");
        int toIndex = command.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1) {
            throw new MisatoException("Invalid format. Use: event <desc> /from <start> /to <end>.");
        }

        String description = command.substring(COMMAND_EVENT.length(), fromIndex).trim();

        if (fromIndex + 5 >= toIndex) {
            throw new MisatoException("Your time format is weird. Check the /from and /to tags.");
        }

        String from = command.substring(fromIndex + 5, toIndex).trim();
        String to = command.substring(toIndex + 3).trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new MisatoException("The description or time cannot be empty.");
        }

        addTaskToList(new Event(description, from, to));
    }

    private static void addTaskToList(Task task) {
        tasks.add(task);

        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printLine();


        saveTasks();
    }

    private static void findTasks(String command) throws MisatoException {
        String keyword = command.substring(COMMAND_FIND.length()).trim();

        if (keyword.isEmpty()) {
            throw new MisatoException("What exactly am I supposed to find? Give me a keyword!");
        }

        printLine();
        System.out.println("Here are the matching tasks in your list:");

        int displayIndex = 1;
        for (Task task : tasks) {
            if (task.contains(keyword)) {
                System.out.println(displayIndex + "." + task.toString());
                displayIndex++;
            }
        }

        if (displayIndex == 1) {
            System.out.println("Baka. Do you even know what you're looking for?");
        }
        printLine();
    }

    // PRINT HELPERS

    private static void printError(String message) {
        printLine();
        System.out.println("☹ OOPS!!! " + message);
        printLine();
    }

    private static void printGreeting() {
        printLine();
        System.out.println("Hello! I'm Misato Katsuragi\nWhat can I do for you?");
        printLine();
    }

    private static void printExit() {
        printLine();
        System.out.println("""
                Bye bye!
                Find for yourself why you came here. And...
                when you're finished... come back.
                Promise me.
                Have a good time.""");
        printLine();
    }

    public static void printLine() {
        System.out.println(LINE);
    }
}
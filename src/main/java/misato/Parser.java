package main.java.misato;

import main.java.misato.commands.AddCommand;
import main.java.misato.commands.Command;
import main.java.misato.commands.DeleteCommand;
import main.java.misato.commands.ExitCommand;
import main.java.misato.commands.FindCommand;
import main.java.misato.commands.ListCommand;
import main.java.misato.commands.MarkCommand;
import main.java.misato.exceptions.MisatoException;
import main.java.misato.tasks.Deadline;
import main.java.misato.tasks.Event;
import main.java.misato.tasks.Todo;

/**
 * Parses user input strings and translates them into executable Commands.
 */
public class Parser {

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_BYE_ALT = "bye bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_FIND = "find";

    /**
     * Interprets the raw input string and creates the corresponding Command object.
     *
     * @param userInput The string entered by the user.
     * @return The specific Command object to be executed.
     * @throws MisatoException If the user input does not match any known command format.
     */
    public static Command parse(String userInput) throws MisatoException {
        if (userInput.equalsIgnoreCase(COMMAND_BYE) || userInput.equalsIgnoreCase(COMMAND_BYE_ALT)) {
            return new ExitCommand();
        }

        if (userInput.equalsIgnoreCase(COMMAND_LIST)) {
            return new ListCommand();
        } else if (userInput.startsWith(COMMAND_MARK)) {
            return new MarkCommand(extractIndex(userInput), true);
        } else if (userInput.startsWith(COMMAND_UNMARK)) {
            return new MarkCommand(extractIndex(userInput), false);
        } else if (userInput.startsWith(COMMAND_DELETE)) {
            return new DeleteCommand(extractIndex(userInput));
        } else if (userInput.startsWith(COMMAND_TODO)) {
            return new AddCommand(new Todo(extractDescription(userInput, COMMAND_TODO)));
        } else if (userInput.startsWith(COMMAND_DEADLINE)) {
            return parseDeadline(userInput);
        } else if (userInput.startsWith(COMMAND_EVENT)) {
            return parseEvent(userInput);
        } else if (userInput.startsWith(COMMAND_FIND)) {
            return new FindCommand(extractDescription(userInput, COMMAND_FIND));
        } else {
            throw new MisatoException("I'm sorry, but I don't know what that means.");
        }
    }

    private static Command parseDeadline(String userInput) throws MisatoException {
        int byIndex = userInput.indexOf("/by");
        if (byIndex == -1) {
            throw new MisatoException("Invalid format. Use: deadline <desc> /by <yyyy-MM-dd HHmm>.");
        }

        String desc = userInput.substring(COMMAND_DEADLINE.length(), byIndex).trim();
        String by = userInput.substring(byIndex + 3).trim();

        if (desc.isEmpty() || by.isEmpty()) {
            throw new MisatoException("The description or date cannot be empty.");
        }

        return new AddCommand(new Deadline(desc, by));
    }

    private static Command parseEvent(String userInput) throws MisatoException {
        int fromIndex = userInput.indexOf("/from");
        int toIndex = userInput.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1) {
            throw new MisatoException("Invalid format. Use: event <desc> /from <start> /to <end>.");
        }

        String desc = userInput.substring(COMMAND_EVENT.length(), fromIndex).trim();
        String from = userInput.substring(fromIndex + 5, toIndex).trim();
        String to = userInput.substring(toIndex + 3).trim();

        return new AddCommand(new Event(desc, from, to));
    }

    private static int extractIndex(String command) throws MisatoException {
        try {
            return Integer.parseInt(command.split(" ")[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new MisatoException("Please provide a valid task number.");
        }
    }

    private static String extractDescription(String command, String type) throws MisatoException {
        if (command.length() <= type.length()) {
            throw new MisatoException("The description cannot be empty.");
        }

        String desc = command.substring(type.length()).trim();
        if (desc.isEmpty()) {
            throw new MisatoException("The description cannot be empty.");
        }

        return desc;
    }
}
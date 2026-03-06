package main.java.misato.commands;

import main.java.misato.Storage;
import main.java.misato.TaskList;
import main.java.misato.Ui;
import main.java.misato.exceptions.MisatoException;

/**
 * Abstract base class representing an executable command.
 */
public abstract class Command {
    /**
     * Executes the command's logic.
     *
     * @param tasks   The TaskList currently in memory.
     * @param ui      The Ui responsible for printing messages.
     * @param storage The Storage responsible for writing changes to the disk.
     * @throws MisatoException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MisatoException;

    /**
     * Indicates whether this command should terminate the application.
     *
     * @return True if the application should close, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
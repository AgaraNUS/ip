package main.java.misato.commands;

import main.java.misato.Storage;
import main.java.misato.TaskList;
import main.java.misato.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Exiting logic is gracefully handled by the boolean flag in Misato.java
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
package misato.commands;

import misato.Storage;
import misato.TaskList;
import misato.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Exiting logic is handled by boolean flag in Misato.java
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
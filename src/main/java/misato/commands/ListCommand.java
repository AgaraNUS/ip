package main.java.misato.commands;

import main.java.misato.Storage;
import main.java.misato.TaskList;
import main.java.misato.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.showMessage((i + 1) + "." + tasks.getTask(i).toString());
        }
        if (tasks.isEmpty()) {
            ui.showMessage("It all... returns... to nothing...");
        }
    }
}
package misato.commands;

import misato.Storage;
import misato.TaskList;
import misato.Ui;
import misato.exceptions.MisatoException;
import misato.tasks.Task;
import java.io.IOException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MisatoException {
        if (tasks.containsDuplicate(task)) {
            throw new MisatoException("Baka! You already have this exact same task in your list.");
        }
        tasks.addTask(task);
        ui.showMessage("Got it. I've added this task:\n  " + task.toString() + "\nNow you have " + tasks.size() + " tasks in the list.");
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showError("I couldn't save your tasks to the hard disk!");
        }
    }
}
package misato.commands;

import misato.Storage;
import misato.TaskList;
import misato.Ui;
import misato.exceptions.MisatoException;
import misato.tasks.Task;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MisatoException {
        try {
            Task removedTask = tasks.removeTask(index);
            ui.showMessage("You better have a good reason for this!\n  " + removedTask.toString() + "\nNow you have " + tasks.size() + " tasks in the list.");
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new MisatoException("Task number is out of range.");
        } catch (Exception e) {
            ui.showError("I couldn't save your tasks to the hard disk!");
        }
    }
}
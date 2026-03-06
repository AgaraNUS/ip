package main.java.misato.commands;

import main.java.misato.Storage;
import main.java.misato.TaskList;
import main.java.misato.Ui;
import main.java.misato.exceptions.MisatoException;
import main.java.misato.tasks.Task;

public class MarkCommand extends Command {
    private int index;
    private boolean isDone;

    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MisatoException {
        try {
            Task task = tasks.getTask(index);

            if (this.isDone) {
                // Check if already done
                if (task.isDone()) {
                    ui.showMessage("This task has already been marked as done");
                } else {
                    task.markAsDone();
                    ui.showMessage("Omedeto! OP-kun!\n  " + task.toString());
                    storage.save(tasks); // Only save if a change was actually made
                }
            } else {
                // Check if already unmarked
                if (!task.isDone()) {
                    ui.showMessage("This task has already been unmarked");
                } else {
                    task.markAsUndone();
                    ui.showMessage("Tsk, just get on with it already\n  " + task.toString());
                    storage.save(tasks); // Only save if a change was actually made
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new MisatoException("Task number out of range.");
        } catch (Exception e) {
            ui.showError("I couldn't save your tasks to the hard disk!");
        }
    }
}
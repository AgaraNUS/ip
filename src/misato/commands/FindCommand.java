package misato.commands;

import misato.Storage;
import misato.TaskList;
import misato.Ui;
import misato.tasks.Task;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the matching tasks in your list:");
        int displayIndex = 1;
        for (Task task : tasks.getTasks()) {
            if (task.contains(keyword)) {
                ui.showMessage(displayIndex + "." + task.toString());
                displayIndex++;
            }
        }
        if (displayIndex == 1) {
            ui.showMessage("Baka. Do you even know what you're looking for?");
        }
    }
}
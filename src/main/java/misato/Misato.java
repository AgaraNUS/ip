package main.java.misato;

import main.java.misato.commands.Command;
import main.java.misato.exceptions.MisatoException;
import java.nio.file.Paths;

/**
 * The main entry point for the Misato chatbot application.
 * Initializes the UI, Storage, and TaskList, and runs the main interaction loop.
 */
public class Misato {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Misato instance with the specified file path for storage.
     *
     * @param filePath The relative path to the data file.
     */
    public Misato(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the chatbot interaction loop.
     */
    public void run() {
        ui.showWelcome();
        boolean isRunning = true;

        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isRunning = !c.isExit();
            } catch (MisatoException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        ui.showGoodbye();
        ui.showLine();
    }

    public static void main(String[] args) {
        String filePath = Paths.get(".", "data", "misato.txt").toString();
        new Misato(filePath).run();
    }
}
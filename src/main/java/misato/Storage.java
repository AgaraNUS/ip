package main.java.misato;

import main.java.misato.tasks.Deadline;
import main.java.misato.tasks.Event;
import main.java.misato.tasks.Task;
import main.java.misato.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles reading from and writing to the hard disk to persist task data.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage instance.
     *
     * @param filePath The path where the task data is saved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the text file.
     *
     * @return An ArrayList containing the parsed Task objects.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return loadedTasks;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                try {
                    Task task = parseLineToTask(line);
                    loadedTasks.add(task);
                } catch (Exception e) {
                    System.out.println("Skipping corrupted data line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the data file.");
        }
        return loadedTasks;
    }

    /**
     * Saves the current tasks to the text file.
     *
     * @param taskList The TaskList object containing the tasks to save.
     * @throws IOException If the file cannot be written to.
     */
    public void save(TaskList taskList) throws IOException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (FileWriter fw = new FileWriter(file)) {
            for (Task task : taskList.getTasks()) {
                fw.write(task.toFileFormat() + System.lineSeparator());
            }
        }
    }

    private Task parseLineToTask(String line) throws Exception {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                task = new Deadline(description, parts[3]);
                break;
            case "E":
                task = new Event(description, parts[3], parts[4]);
                break;
            default:
                throw new Exception("Unknown task type");
        }

        // Added standard braces here
        if (isDone) {
            task.markAsDone();
        }

        return task;
    }
}
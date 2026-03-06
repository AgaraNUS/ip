package main.java.misato;

import main.java.misato.tasks.Task;
import java.util.ArrayList;

/**
 * Wrapper class that holds the list of tasks and provides methods to manipulate it.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public boolean containsDuplicate(Task task) {
        return tasks.contains(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
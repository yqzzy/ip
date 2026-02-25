package task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task deleteTask(int index) {
        return taskList.remove(index);
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public Task findTaskByDescription(String description) {
        for (Task task : taskList) {
            if (task.getDescription().equals(description)) {
                return task;
            }
        }
        return null;
    }

}

package interface_adapter.get_task;

import entity.Task;

import java.util.ArrayList;

public class GetTaskState {
    private ArrayList<Task> tasks;
    private String message;

    public GetTaskState() {}

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String getMessage() {return message;}

    public void setMessage(String message) {this.message = message;}
}

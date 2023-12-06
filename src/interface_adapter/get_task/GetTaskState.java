package interface_adapter.get_task;

import java.util.ArrayList;

public class GetTaskState {
//    private ArrayList<Task> tasks;
    ArrayList<String> taskName;
    ArrayList<String> taskId;
    ArrayList<String> taskDeadline;
    private String message;

    public GetTaskState() {}

//    public ArrayList<Task> getTasks() {
//        return tasks;
//    }
//
//    public void setTasks(ArrayList<Task> tasks) {
//        this.tasks = tasks;
//    }

    public ArrayList<String> getTaskName() {
        return taskName;
    }

    public void setTaskName(ArrayList<String> taskName) {
        this.taskName = taskName;
    }

    public ArrayList<String> getTaskId() {
        return taskId;
    }

    public void setTaskId(ArrayList<String> taskId) {
        this.taskId = taskId;
    }

    public ArrayList<String> getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(ArrayList<String> taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public String getMessage() {return message;}

    public void setMessage(String message) {this.message = message;}
}

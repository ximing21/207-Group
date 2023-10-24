package use_case;

public class TodolistInputData {


    private String task_name;
    private String description;
    private String deadline;

    public TodolistInputData(String task_name, String description, String deadline) {
        this.task_name = task_name;
        this.description = description;
        this.deadline = deadline;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}

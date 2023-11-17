package use_case.add_task;

public class AddTaskInputData {
    final private String taskName;
    final private String deadline;

    public AddTaskInputData(String taskName, String deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDeadline() {
        return deadline;
    }
}

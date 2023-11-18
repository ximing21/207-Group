package use_case.add_task;

public class AddTaskOutputData {
    private final String name;
    private final String taskId;
    private final String deadline;

    public AddTaskOutputData(String name, String taskId, String deadline) {
        this.name = name;
        this.taskId = taskId;
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public String gettaskId() {
        return taskId;
    }

    public String getdeadline() {
        return deadline;
    }
}
package use_case.add_task;

public class AddTaskOutputData {
    private final String name;
    private final String taskId;

    public AddTaskOutputData(String name, String taskId) {
        this.name = name;
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public String gettaskId() {
        return taskId;
    }
}
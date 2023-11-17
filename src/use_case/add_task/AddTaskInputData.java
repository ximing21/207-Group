package use_case.add_task;

public class AddTaskInputData {
    final private String taskName;
    final private String deadline;
    final private String projectName;

    public AddTaskInputData(String taskName, String deadline, String projectName) {
        this.taskName = taskName;
        this.deadline = deadline;
        this.projectName = projectName;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getProjectName() {
        return projectName;
    }
}

package interface_adapter.add_task;


public class AddTaskState {
    private String project_name = "";
    private String task_name = "";
    private String taskId = "";
    private String taskDeadline;

    public AddTaskState() {}

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(String deadline) {
        this.taskDeadline = deadline;
    }

    public String getTask_name() {
        return task_name;
    }
    public String getTaskId() {
        return taskId;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}

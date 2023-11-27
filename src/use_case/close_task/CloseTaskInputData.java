package use_case.close_task;


public class CloseTaskInputData {
    final private String taskId;

    public CloseTaskInputData(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }
}

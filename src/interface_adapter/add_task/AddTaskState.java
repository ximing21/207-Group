package interface_adapter.add_task;

import interface_adapter.add_project.AddProjectState;

public class AddTaskState {
    private String project_name = "";
    private String project_nameError = null;
    private String task_name = "";
    private String taskId = "";
    private String task_nameError = null;


    public AddTaskState(AddTaskState copy) {
        project_name = copy.project_name;
        project_nameError = copy.project_nameError;
        task_name = copy.task_name;
        taskId = copy.taskId;
        task_nameError = copy.task_nameError;
    }

    public AddTaskState() {}

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_nameError() {
        return project_nameError;
    }

    public void setProject_nameError(String project_nameError) {
        this.project_nameError = project_nameError;
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

    public String getTask_nameError() {
        return task_nameError;
    }

    public void setTask_nameError(String task_nameError) {
        this.task_nameError = task_nameError;
    }
}

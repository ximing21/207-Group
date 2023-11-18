package entity;

public class Task{
    private String taskId;
    private String taskName;
    private String projectId;
    private Boolean isCompleted;
    private String deadline;


    public Task(String taskId, String taskName, String projectId, Boolean isCompleted, String deadline) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.projectId = projectId;
        this.isCompleted = isCompleted;
        this.deadline = deadline;
    }

    public boolean IsCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }


    public static TaskBuilder builder() {return new TaskBuilder();}

    public static class TaskBuilder {
        private String taskId;
        private String taskName;
        private String projectId;
        private Boolean isCompleted;
        private String deadline;

        TaskBuilder() {
        }

        public TaskBuilder TaskName(String taskName) {
            this.taskName = taskName;
            return this;
        }

        public TaskBuilder ProjectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public TaskBuilder TaskId(String taskId) {
            this.taskId = taskId;
            return this;
        }

        public TaskBuilder IsCompleted(Boolean isCompleted) {
            this.isCompleted = isCompleted;
            return this;
        }

        public TaskBuilder Deadline(String deadline) {
            this.deadline = deadline;
            return this;
        }


        public Task build() {
            return new Task(taskId, taskName, projectId, isCompleted, deadline);
        }

    }

    public String getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getDeadline() {
        return deadline;
    }


}

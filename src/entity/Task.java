package entity;

public class Task{
    private String taskId;
    private String taskName;
    private String projectId;
    private String deadline;


    public Task(String taskId, String taskName, String projectId, String deadline) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.projectId = projectId;
        this.deadline = deadline;
    }

    public static TaskBuilder builder() {return new TaskBuilder();}

    public static class TaskBuilder {
        private String taskId;
        private String taskName;
        private String projectId;
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

        public TaskBuilder Deadline(String deadline) {
            this.deadline = deadline;
            return this;
        }

        public Task build() {
            return new Task(taskId, taskName, projectId, deadline);
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

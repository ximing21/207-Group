package entity;

public class Task{
    private String TaskId;
    private String TaskName;
    private String ProjectId;

    public Task(String TaskName, String ProjectId) {
        this.TaskName = TaskName;
        this.ProjectId = ProjectId;
    }
    public Task(String TaskId, String TaskName, String ProjectId) {
        this.TaskId = TaskId;
        this.TaskName = TaskName;
        this.ProjectId = ProjectId;
    }

    public static TaskBuilder builder() {return new TaskBuilder();}

    public static class TaskBuilder {
        private String TaskId;
        private String TaskName;
        private String ProjectId;

        TaskBuilder() {
        }

        public TaskBuilder TaskName(String TaskName) {
            this.TaskName = TaskName;
            return this;
        }

        public TaskBuilder ProjectId(String ProjectId) {
            this.ProjectId = ProjectId;
            return this;
        }

        public TaskBuilder TaskId(String TaskId) {
            this.TaskId = TaskId;
            return this;
        }

        public Task build() {
            return new Task(TaskId, TaskName, ProjectId);
        }

    }

    public String getTaskId() {
        return TaskId;
    }

    public String getName() {
        return TaskName;
    }

    public String getProjectId() {
        return ProjectId;
    }
}

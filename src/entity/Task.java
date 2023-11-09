package entity;

public class Task{
    private String taskName;
//    private String priority;
    private Boolean isCompleted;
    private final String projectId;


    public Task(String taskName, Boolean isCompleted, String project_id) {
        this.taskName = taskName;
//        this.priority = priority;
        this.isCompleted = isCompleted;
        this.projectId = project_id;
    }

    public static TaskBuilder builder() {return new TaskBuilder();}

    public static class TaskBuilder {
        private String taskName;
        private Boolean isCompleted;
        private String projectId;

        TaskBuilder() {
        }

        public TaskBuilder taskName(String name) {
            this.taskName = name;
            return this;
        }

        public TaskBuilder isCompleted(Boolean isCompleted) {
            this.isCompleted = isCompleted;
            return this;
        }

        public TaskBuilder projectId(String project_id) {
            this.projectId = project_id;
            return this;
        }

        public Task build() {return new Task(taskName, isCompleted, projectId);}
    }

    public String getName() {
        return taskName;
    }


//    public String getPriority() {
//        return priority;
//    }
//
//
    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public String getProjectId() {
        return projectId;
    }
}

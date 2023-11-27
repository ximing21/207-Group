package entity;

public class Project {
    private String projectId;
    private String projectName;
    private int taskCount;

    public Project(String projectId, String projectName, int taskCount) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.taskCount = taskCount;
    }

    public static ProjectBuilder builder() {
        return new ProjectBuilder();
    }

    public static  class ProjectBuilder {
        private String projectId;
        private String projectName;
        private int taskCount;


        ProjectBuilder(){}

        public ProjectBuilder ProjectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public ProjectBuilder ProjectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public ProjectBuilder TaskCount(int taskCount) {
            this.taskCount = taskCount;
            return this;
        }

        public Project build() {
            return new Project(projectId, projectName, taskCount);}
    }

    public String getName() {
        return this.projectName;
    }

    public int getTaskCount() {
        return this.taskCount;
    }
}
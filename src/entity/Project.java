package entity;

public class Project {
    private String ProjectId;
    private String ProjectName;
    private int taskCount;

    public Project(String ProjectId, String ProjectName, int taskCount) {
        this.ProjectId = ProjectId;
        this.ProjectName = ProjectName;
        this.taskCount = taskCount;
    }

    public static ProjectBuilder builder() {
        return new ProjectBuilder();
    }

    public static  class ProjectBuilder {
        private String ProjectId;
        private String ProjectName;
        private int taskCount;


        ProjectBuilder(){}

        public ProjectBuilder ProjectName(String ProjectName) {
            this.ProjectName = ProjectName;
            return this;
        }

        public ProjectBuilder ProjectId(String ProjectId) {
            this.ProjectId = ProjectId;
            return this;
        }

        public Project build() {
            return new Project(ProjectId, ProjectName, taskCount);}

        public ProjectBuilder TaskCount(int taskCount) {
            this.taskCount = taskCount;
            return this;
        }
    }

    public String getName() {
        return this.ProjectName;
    }

    public String getId() {
        return this.ProjectId;
    }

    public int getTaskCount() {
        return this.taskCount;
    }


}
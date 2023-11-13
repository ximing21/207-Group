package entity;

public class Project {
    private String ProjectId;
    private String ProjectName;

    public Project(String ProjectId, String ProjectName) {
        this.ProjectId = ProjectId;
        this.ProjectName = ProjectName;
    }

    public static ProjectBuilder builder() {
        return new ProjectBuilder();
    }

    public static  class ProjectBuilder {
        private String ProjectId;
        private String ProjectName;


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
            return new Project(ProjectId, ProjectName);}
    }

    public String getName() {
        return this.ProjectName;
    }

    public String getId() {
        return this.ProjectId;
    }


}
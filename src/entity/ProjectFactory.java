package entity;

public class ProjectFactory {
    public Project create(String projectId, String projectName, int taskCount) {
        return new Project(projectId, projectName, taskCount);
    }
}

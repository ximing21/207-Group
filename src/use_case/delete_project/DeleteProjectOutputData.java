package use_case.delete_project;


public class DeleteProjectOutputData {
    private String projectName;
    public DeleteProjectOutputData(String projectName) {
        this.projectName = projectName;}

    public String getProjectName() {
        return projectName;
    }
}

package use_case.delete_project;

import kotlin.Pair;

public class DeleteProjectOutputData {
    private String projectName;
    public DeleteProjectOutputData(String projectName) {
        this.projectName = projectName;}

    public String getProjectName() {
        return projectName;
    }
}

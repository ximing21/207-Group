package use_case.delete_project;

import kotlin.Pair;

public class DeleteProjectOutputData {
    private final String projectName;
    private final Integer count;
    public DeleteProjectOutputData(Pair<String, Integer> project) {
        this.projectName = project.getFirst();
        this.count= project.getSecond(); }

    public String getProjectName() {
        return projectName;
    }

    public Integer getCount() {
        return count;
    }
}

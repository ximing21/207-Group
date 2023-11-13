package use_case.get_all_projects;

import entity.Project;

public class GetProjectOutputData {
    private final Project[] projects;

    public GetProjectOutputData(Project[] projects) {
        this.projects = projects;
    }

    public Project[] getPojects() {
        return projects;
    }
}
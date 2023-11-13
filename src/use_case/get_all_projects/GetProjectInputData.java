package use_case.get_all_projects;

import entity.Project;

public class GetProjectInputData {
    final private Project[] projects;

    public GetProjectInputData(Project[] projects) {
        this.projects = projects;
    }

    public Project[] getProjects() {
        return projects;
    }
}

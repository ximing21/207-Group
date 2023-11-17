package use_case.close_task;

import entity.Project;

public class CloseTaskOutputData {
    private final Project[] projects;

    public CloseTaskOutputData(Project[] projects) {
        this.projects = projects;
    }

    public Project[] getPojects() {
        return projects;
    }
}
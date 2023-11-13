package interface_adapter.get_all_projects;

import entity.Project;
import use_case.add_project.AddProjectInputData;
import use_case.get_all_projects.GetProjectInputBoundary;
import use_case.get_all_projects.GetProjectInputData;

public class GetProjectController {
    final GetProjectInputBoundary getProjectInteractor;
    public GetProjectController(GetProjectInputBoundary getProjectInteractor) {
        this.getProjectInteractor = getProjectInteractor;
    }

    public void execute() {
        getProjectInteractor.execute();
    }
}

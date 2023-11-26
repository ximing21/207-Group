package interface_adapter.get_all_projects;


import use_case.get_all_projects.GetProjectInputBoundary;

public class GetProjectController {
    final GetProjectInputBoundary getProjectInteractor;
    public GetProjectController(GetProjectInputBoundary getProjectInteractor) {
        this.getProjectInteractor = getProjectInteractor;
    }

    public void execute() {
        getProjectInteractor.execute();
    }
}

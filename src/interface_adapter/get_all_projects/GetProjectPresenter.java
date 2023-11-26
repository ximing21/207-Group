package interface_adapter.get_all_projects;

import interface_adapter.ViewManagerModel;
import use_case.get_all_projects.GetProjectOutputBoundary;
import use_case.get_all_projects.GetProjectOutputData;

public class GetProjectPresenter implements GetProjectOutputBoundary {
    private final GetProjectViewModel getProjectViewModel;
    private final ViewManagerModel viewManagerModel;


    public GetProjectPresenter(GetProjectViewModel getProjectViewModel, ViewManagerModel viewManagerModel) {
        this.getProjectViewModel = getProjectViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(GetProjectOutputData response) {
        // On success, a message window pops up saying the project is successfully created
        GetProjectState getProjectState = getProjectViewModel.getState();
        getProjectState.setProjects(response.getPojects());
        getProjectViewModel.setState(getProjectState);
        getProjectViewModel.firePropertyChanged(); // triggers update UI
    }

    @Override
    public void prepareFailView(String error) {
    }
}

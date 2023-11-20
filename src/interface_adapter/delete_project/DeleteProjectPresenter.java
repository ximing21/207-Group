package interface_adapter.delete_project;

import interface_adapter.ViewManagerModel;
import use_case.delete_project.DeleteProjectOutputBoundary;
import use_case.delete_project.DeleteProjectOutputData;

public class DeleteProjectPresenter implements DeleteProjectOutputBoundary {
    private final DeleteProjectViewModel deleteProjectViewModel;
    private final ViewManagerModel viewManagerModel;

    public DeleteProjectPresenter(DeleteProjectViewModel deleteProjectViewModel, ViewManagerModel viewManagerModel) {
        this.deleteProjectViewModel = deleteProjectViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DeleteProjectOutputData response) {
        DeleteProjectState deleteProjectState = deleteProjectViewModel.getState();
        String projectName = response.getProjectName();

        deleteProjectState.setProject_name(projectName);
        deleteProjectViewModel.setState(deleteProjectState);
        deleteProjectViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}

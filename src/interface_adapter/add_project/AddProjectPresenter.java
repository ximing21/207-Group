package interface_adapter.add_project;

import interface_adapter.ViewManagerModel;
import interface_adapter.added_project.AddedProjectState;
import interface_adapter.added_project.AddedProjectViewModel;
import use_case.add_project.AddProjectOutputBoundary;
import use_case.add_project.AddProjectOutputData;

public class AddProjectPresenter implements AddProjectOutputBoundary {
    private final AddProjectViewModel addProjectViewModel;
    private final AddedProjectViewModel addedProjectViewModel;
    private final ViewManagerModel viewManagerModel;


    public AddProjectPresenter(AddProjectViewModel addProjectViewModel, AddedProjectViewModel addedProjectViewModel, ViewManagerModel viewManagerModel) {
        this.addProjectViewModel = addProjectViewModel;
        this.addedProjectViewModel = addedProjectViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(AddProjectOutputData response) {
        // On success, a message window pops up saying the project is successfully created
        AddedProjectState addedProjectState = addedProjectViewModel.getState();
        addedProjectState.setProjectname(response.getName());
        this.addedProjectViewModel.setState(addedProjectState);
        addedProjectViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        AddProjectState addProjectState = addProjectViewModel.getState();
        addProjectState.setProject_nameError(error);
        addProjectViewModel.firePropertyChanged();
    }
}

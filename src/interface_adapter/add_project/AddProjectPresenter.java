package interface_adapter.add_project;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_task.AddTaskState;
import interface_adapter.add_task.AddTaskViewModel;
import use_case.add_project.AddProjectOutputBoundary;
import use_case.add_project.AddProjectOutputData;

public class AddProjectPresenter implements AddProjectOutputBoundary {
    private final AddProjectViewModel addProjectViewModel;
    private final AddTaskViewModel addTaskViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddProjectPresenter(AddProjectViewModel addProjectViewModel, AddTaskViewModel addTaskViewModel, ViewManagerModel viewManagerModel) {
        this.addProjectViewModel = addProjectViewModel;
        this.addTaskViewModel = addTaskViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(AddProjectOutputData response) {
        // On success, switch to add task view
        AddTaskState addTaskState = addTaskViewModel.getState();
        addTaskState.setProject_name(response.getName());
        this.addTaskViewModel.setState(addTaskState);
        addTaskViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(addTaskViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        AddProjectState addProjectState = addProjectViewModel.getState();
        addProjectState.setProject_nameError(error);
        addProjectViewModel.firePropertyChanged();
    }
}

package app;

import api.TodoistDB;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_project.AddProjectController;
import interface_adapter.add_project.AddProjectPresenter;
import interface_adapter.add_project.AddProjectViewModel;
import interface_adapter.add_task.AddTaskViewModel;
import interface_adapter.added_project.AddedProjectViewModel;
import use_case.add_project.AddProjectDataAccessInterface;
import use_case.add_project.AddProjectInputBoundary;
import use_case.add_project.AddProjectInteractor;
import use_case.add_project.AddProjectOutputBoundary;
import view.AddProjectView;

public class AddProjectUseCaseFactory {
    private AddProjectUseCaseFactory() {}

    public static AddProjectView create(ViewManagerModel viewManagerModel,
                                        AddedProjectViewModel addedProjectViewModel,
                                        AddProjectViewModel addProjectViewModel,
                                        TodoistDB userDataAccessObject){
        AddProjectController addProjectController = createAddProjectUseCase(viewManagerModel, addProjectViewModel, addedProjectViewModel, userDataAccessObject);
        return new AddProjectView(addProjectViewModel, addProjectController, addedProjectViewModel);
    }

    private static AddProjectController createAddProjectUseCase(ViewManagerModel viewManagerModel,
                                                                AddProjectViewModel addProjectViewModel,
                                                                AddedProjectViewModel addedProjectViewModel,
                                                                AddProjectDataAccessInterface userDataAccessObject) {

        AddProjectOutputBoundary addProjectOutputBoundary = new AddProjectPresenter(addProjectViewModel, addedProjectViewModel, viewManagerModel);

        AddProjectInputBoundary addProjectInteractor = new AddProjectInteractor(addProjectOutputBoundary, userDataAccessObject);
        return new AddProjectController(addProjectInteractor);
    }
}

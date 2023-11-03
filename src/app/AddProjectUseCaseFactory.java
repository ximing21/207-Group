package app;

import api.TodoistDB;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_project.AddProjectController;
import interface_adapter.add_project.AddProjectPresenter;
import interface_adapter.add_project.AddProjectViewModel;
import interface_adapter.add_task.AddTaskViewModel;
import use_case.add_project.AddProjectDataAccessInterface;
import use_case.add_project.AddProjectInputBoundary;
import use_case.add_project.AddProjectInteractor;
import use_case.add_project.AddProjectOutputBoundary;
import view.AddProjectView;

public class AddProjectUseCaseFactory {
    private AddProjectUseCaseFactory() {}

    public static AddProjectView create(ViewManagerModel viewManagerModel,
                                        AddTaskViewModel addTaskViewModel,
                                        AddProjectViewModel addProjectViewModel,
                                        TodoistDB userDataAccessObject){
        AddProjectController addProjectController = createAddProjectUseCase(viewManagerModel, addProjectViewModel, addTaskViewModel, userDataAccessObject);
        return new AddProjectView(addProjectViewModel, addProjectController);
    }

    private static AddProjectController createAddProjectUseCase(ViewManagerModel viewManagerModel,
                                                                AddProjectViewModel addProjectViewModel,
                                                                AddTaskViewModel addTaskViewModel,
                                                                AddProjectDataAccessInterface userDataAccessObject) {
        AddProjectOutputBoundary addProjectOutputBoundary = new AddProjectPresenter(addProjectViewModel, addTaskViewModel, viewManagerModel);

        AddProjectInputBoundary addProjectInteractor = new AddProjectInteractor(addProjectOutputBoundary, userDataAccessObject);
        return new AddProjectController(addProjectInteractor);
    }



}

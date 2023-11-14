package app;

import api.TodoistDB;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_project.AddProjectController;
import interface_adapter.add_project.AddProjectPresenter;
import interface_adapter.add_project.AddProjectViewModel;
import interface_adapter.added_project.AddedProjectViewModel;
import interface_adapter.get_all_projects.GetProjectController;
import interface_adapter.get_all_projects.GetProjectPresenter;
import interface_adapter.get_all_projects.GetProjectViewModel;
import interface_adapter.get_task.GetTaskController;
import interface_adapter.get_task.GetTaskPresenter;
import interface_adapter.get_task.GetTaskViewModel;
import use_case.add_project.AddProjectDataAccessInterface;
import use_case.add_project.AddProjectInputBoundary;
import use_case.add_project.AddProjectInteractor;
import use_case.add_project.AddProjectOutputBoundary;
import use_case.get_all_projects.GetProjectDataAccessInterface;
import use_case.get_all_projects.GetProjectInputBoundary;
import use_case.get_all_projects.GetProjectInteractor;
import use_case.get_all_projects.GetProjectOutputBoundary;
import use_case.get_task.GetTaskDataAccessInterface;
import use_case.get_task.GetTaskInputBoundary;
import use_case.get_task.GetTaskInteractor;
import use_case.get_task.GetTaskOutputBoundary;
import view.AddProjectView;

public class AddProjectUseCaseFactory {
    private AddProjectUseCaseFactory() {}

    public static AddProjectView create(ViewManagerModel viewManagerModel,
                                        AddedProjectViewModel addedProjectViewModel,
                                        AddProjectViewModel addProjectViewModel,
                                        TodoistDB userDataAccessObject,
                                        GetProjectViewModel getProjectViewModel,
                                        GetTaskViewModel getTaskViewModel
                                        ){
        AddProjectController addProjectController = createAddProjectUseCase(viewManagerModel, addProjectViewModel, addedProjectViewModel, userDataAccessObject);
        GetProjectController getProjectController = createGetProjectUseCase(viewManagerModel, getProjectViewModel, userDataAccessObject);
        GetTaskController getTaskController = createGetTaskUseCase(viewManagerModel, getTaskViewModel, userDataAccessObject);
        return new AddProjectView(addProjectViewModel, addProjectController, addedProjectViewModel, getProjectViewModel, getProjectController, getTaskController);
    }

    private static AddProjectController createAddProjectUseCase(ViewManagerModel viewManagerModel,
                                                                AddProjectViewModel addProjectViewModel,
                                                                AddedProjectViewModel addedProjectViewModel,
                                                                AddProjectDataAccessInterface userDataAccessObject) {

        AddProjectOutputBoundary addProjectOutputBoundary = new AddProjectPresenter(addProjectViewModel, addedProjectViewModel, viewManagerModel);

        AddProjectInputBoundary addProjectInteractor = new AddProjectInteractor(addProjectOutputBoundary, userDataAccessObject);
        return new AddProjectController(addProjectInteractor);
    }

    private static GetProjectController createGetProjectUseCase(ViewManagerModel viewManagerModel,
                                                                GetProjectViewModel getProjectViewModel,
                                                                GetProjectDataAccessInterface userDataAccessObject) {

        GetProjectOutputBoundary getProjectOutputBoundary = new GetProjectPresenter(getProjectViewModel, viewManagerModel);

        GetProjectInputBoundary getProjectInteractor = new GetProjectInteractor(getProjectOutputBoundary, userDataAccessObject);
        return new GetProjectController(getProjectInteractor);
    }

    private  static GetTaskController createGetTaskUseCase(ViewManagerModel viewManagerModel,
                                                           GetTaskViewModel getTaskViewModel,
                                                           GetTaskDataAccessInterface userDataAccessObject) {
        GetTaskOutputBoundary getTaskOutputBoundary = new GetTaskPresenter(getTaskViewModel, viewManagerModel);
        GetTaskInputBoundary getTaskInputBoundary = new GetTaskInteractor(getTaskOutputBoundary, userDataAccessObject);
        return new GetTaskController(getTaskInputBoundary);
    }
}

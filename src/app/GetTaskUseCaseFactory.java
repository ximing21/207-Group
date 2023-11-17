package app;

import api.TodoistDB;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_project.AddProjectViewModel;
import interface_adapter.close_task.CloseTaskController;
import interface_adapter.close_task.CloseTaskPresenter;
import interface_adapter.close_task.CloseTaskViewModel;
import interface_adapter.get_task.GetTaskViewModel;
import interface_adapter.switch_view.SwitchViewController;
import interface_adapter.switch_view.SwitchViewPresenter;
import use_case.close_task.CloseTaskDataAccessInterface;
import use_case.close_task.CloseTaskInputBoundary;
import use_case.close_task.CloseTaskInteractor;
import use_case.close_task.CloseTaskOutputBoundary;
import use_case.switch_view.SwitchViewInputBoundary;
import use_case.switch_view.SwitchViewInteractor;
import use_case.switch_view.SwitchViewOutputBoundary;
import view.GetTaskView;

public class GetTaskUseCaseFactory {

    private GetTaskUseCaseFactory() {
    }

    public static GetTaskView create(GetTaskViewModel getTaskViewModel,
                                     ViewManagerModel viewManagerModel,
                                     AddProjectViewModel addProjectViewModel,
                                     CloseTaskViewModel closeTaskViewModel,
                                     TodoistDB userDataAccessObject) {
        SwitchViewController switchViewController = switchViewControllerUseCase(viewManagerModel, addProjectViewModel);
        CloseTaskController closeTaskController = createCloseTaskUseCase(viewManagerModel, closeTaskViewModel, userDataAccessObject);
        return new GetTaskView(getTaskViewModel, switchViewController, closeTaskController);
    }

    private static CloseTaskController createCloseTaskUseCase(ViewManagerModel viewManagerModel, CloseTaskViewModel closeTaskViewModel, CloseTaskDataAccessInterface closeTaskDataAccessInterface) {
        CloseTaskOutputBoundary closeTaskOutputBoundary = new CloseTaskPresenter(closeTaskViewModel, viewManagerModel);
        CloseTaskInputBoundary closeTaskInteractor = new CloseTaskInteractor(closeTaskOutputBoundary, closeTaskDataAccessInterface);
        return new CloseTaskController(closeTaskInteractor);
    }

    private static SwitchViewController switchViewControllerUseCase(ViewManagerModel viewManagerModel, AddProjectViewModel addProjectViewModel) {

        SwitchViewOutputBoundary switchViewPresenter = new SwitchViewPresenter(addProjectViewModel, viewManagerModel);

        SwitchViewInputBoundary switchViewInteractor = new SwitchViewInteractor(switchViewPresenter);
        return new SwitchViewController(switchViewInteractor);
    }

}
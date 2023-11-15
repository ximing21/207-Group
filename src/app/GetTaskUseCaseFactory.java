package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_project.AddProjectViewModel;
import interface_adapter.get_task.GetTaskViewModel;
import interface_adapter.switch_view.SwitchViewController;
import interface_adapter.switch_view.SwitchViewPresenter;
import use_case.switch_view.SwitchViewInputBoundary;
import use_case.switch_view.SwitchViewInteractor;
import use_case.switch_view.SwitchViewOutputBoundary;
import view.GetTaskView;

public class GetTaskUseCaseFactory {

    private GetTaskUseCaseFactory() {
    }

    public static GetTaskView create(GetTaskViewModel getTaskViewModel, ViewManagerModel viewManagerModel, AddProjectViewModel addProjectViewModel) {
        SwitchViewController switchViewController = switchViewControllerUseCase(viewManagerModel, addProjectViewModel);
        return new GetTaskView(getTaskViewModel, switchViewController);
    }
    private static SwitchViewController switchViewControllerUseCase(ViewManagerModel viewManagerModel, AddProjectViewModel addProjectViewModel) {

        SwitchViewOutputBoundary switchViewPresenter = new SwitchViewPresenter(addProjectViewModel, viewManagerModel);

        SwitchViewInputBoundary switchViewInteractor = new SwitchViewInteractor(switchViewPresenter);
        return new SwitchViewController(switchViewInteractor);
    }

}
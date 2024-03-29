package interface_adapter.get_task;

import interface_adapter.ViewManagerModel;
import use_case.get_task.GetTaskOutputBoundary;
import use_case.get_task.GetTaskOutputData;

public class GetTaskPresenter implements GetTaskOutputBoundary {
    private final GetTaskViewModel getTaskViewModel;
    private final ViewManagerModel viewManagerModel;


    public GetTaskPresenter(GetTaskViewModel getTaskViewModel, ViewManagerModel viewManagerModel) {
        this.getTaskViewModel = getTaskViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(GetTaskOutputData response) {
        // On success, switch to task view
        String projectName = response.getProjectName();
        GetTaskState getTaskState = getTaskViewModel.getState();
//        getTaskState.setTasks(response.getTasks());
        getTaskState.setTaskId(response.getTaskId());
        getTaskState.setTaskName(response.getTaskName());
        getTaskState.setTaskDeadline(response.getTaskDeadline());
        getTaskState.setMessage(response.getMessage());
        getTaskViewModel.setState(getTaskState);
        getTaskViewModel.firePropertyChanged();
        getTaskViewModel.setTitleLabel(projectName);
        viewManagerModel.setActiveView(getTaskViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
    }
}

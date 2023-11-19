package interface_adapter.close_task;

import interface_adapter.ViewManagerModel;
import use_case.close_task.CloseTaskOutputBoundary;
import use_case.close_task.CloseTaskOutputData;

public class CloseTaskPresenter implements CloseTaskOutputBoundary {
    private final CloseTaskViewModel closeTaskViewModel;
    private final ViewManagerModel viewManagerModel;


    public CloseTaskPresenter(CloseTaskViewModel closeTaskViewModel, ViewManagerModel viewManagerModel) {
        this.closeTaskViewModel = closeTaskViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CloseTaskOutputData response) {
        CloseTaskState closeTaskState = closeTaskViewModel.getState();
        closeTaskState.setPhrase(response.getPhrase());
        closeTaskViewModel.setState(closeTaskState);
        closeTaskViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}

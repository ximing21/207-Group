package interface_adapter.close_task;

import interface_adapter.ViewManagerModel;
import use_case.close_task.CloseTaskOutputBoundary;

public class CloseTaskPresenter implements CloseTaskOutputBoundary {
    private final CloseTaskViewModel closeTaskViewModel;
    private final ViewManagerModel viewManagerModel;


    public CloseTaskPresenter(CloseTaskViewModel closeTaskViewModel, ViewManagerModel viewManagerModel) {
        this.closeTaskViewModel = closeTaskViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        closeTaskViewModel.setState(new CloseTaskState()); // Reset state or update as needed
        closeTaskViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}

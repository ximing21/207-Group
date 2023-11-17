package interface_adapter.add_task;

import interface_adapter.ViewManagerModel;
import use_case.add_task.AddTaskOutputBoundary;
import use_case.add_task.AddTaskOutputData;

public class AddTaskPresenter implements AddTaskOutputBoundary {
    private final AddTaskViewModel addTaskViewModel;
    private final ViewManagerModel viewManagerModel;


    public AddTaskPresenter(AddTaskViewModel addTaskViewModel, ViewManagerModel viewManagerModel) {
        this.addTaskViewModel = addTaskViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(AddTaskOutputData response) {
    }

    @Override
    public void prepareFailView(String error) {

    }

    public void prepareFailView() {
    }
}

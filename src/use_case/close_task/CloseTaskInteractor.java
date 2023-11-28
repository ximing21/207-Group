package use_case.close_task;


import use_case.add_project.AddProjectOutputData;

public class CloseTaskInteractor implements CloseTaskInputBoundary {
    private final CloseTaskOutputBoundary presenter;
    private final CloseTaskDataAccessInterface dataAccess;

    public CloseTaskInteractor(CloseTaskOutputBoundary presenter, CloseTaskDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(CloseTaskInputData inputData) {
        dataAccess.closeTask(inputData.getTaskId());
        CloseTaskOutputData closeTaskOutputData = new CloseTaskOutputData(true);
        presenter.prepareSuccessView(closeTaskOutputData);
    }
}

package use_case.close_task;

public class CloseTaskInteractor implements CloseTaskInputBoundary {
    private final CloseTaskOutputBoundary presenter;
    private final CloseTaskDataAccessInterface dataAccess;

    public CloseTaskInteractor(CloseTaskOutputBoundary presenter, CloseTaskDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(CloseTaskInputData inputData) {
        boolean success = dataAccess.closeTask(inputData.getTaskId());
        if (success) {
            presenter.prepareSuccessView();
        } else {
            presenter.prepareFailView("Unable to close task.");
        }
    }
}

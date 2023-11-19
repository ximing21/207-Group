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
        dataAccess.closeTask(inputData.getTaskId());
        String phrase = dataAccess.getMessage();
        CloseTaskOutputData result = new CloseTaskOutputData(phrase);
        presenter.prepareSuccessView(result);
    }
}

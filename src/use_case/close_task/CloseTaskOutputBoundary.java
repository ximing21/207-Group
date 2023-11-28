package use_case.close_task;

public interface CloseTaskOutputBoundary {
    void prepareSuccessView(CloseTaskOutputData outputData);

    void prepareFailView(String error);
}

package use_case.close_task;

public interface CloseTaskOutputBoundary {
    void prepareSuccessView();

    void prepareFailView(String error);
}

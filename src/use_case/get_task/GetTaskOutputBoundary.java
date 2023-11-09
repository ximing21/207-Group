package use_case.get_task;


public interface GetTaskOutputBoundary {
    void prepareSuccessView(GetTaskOutputData response);

    void prepareFailView(String error);
}

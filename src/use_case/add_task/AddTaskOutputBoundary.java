package use_case.add_task;

public interface AddTaskOutputBoundary {
    void prepareSuccessView(AddTaskOutputData response);

    void prepareFailView(String error);
}

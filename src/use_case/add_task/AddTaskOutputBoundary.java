package use_case.add_task;

import use_case.add_project.AddProjectOutputData;

public interface AddTaskOutputBoundary {
    void prepareSuccessView(AddTaskOutputData response);

    void prepareFailView(String error);
}

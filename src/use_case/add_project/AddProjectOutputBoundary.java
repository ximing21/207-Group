package use_case.add_project;

public interface AddProjectOutputBoundary {
    void prepareSuccessView(AddProjectOutputData response);

    void prepareFailView(String error);
}

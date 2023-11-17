package use_case.delete_project;

public interface DeleteProjectOutputBoundary {
    void prepareSuccessView(DeleteProjectOutputData response);
    void prepareFailView(String error);
}

package use_case.get_all_projects;

public interface GetProjectOutputBoundary {
    void prepareSuccessView(GetProjectOutputData response);

    void prepareFailView(String error);
}

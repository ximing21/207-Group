package use_case.add_project;

public interface AddProjectDataAccessInterface {
    boolean existsByName(String name);
    void createProject(String name);
}

package use_case.add_project;

import entity.Project;

public interface AddProjectDataAccessInterface {
    boolean existsByName(String name);
    Project createProject(String name);
}

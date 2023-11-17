package use_case.add_task;

public interface AddTaskDataAccessInterface {
    boolean existsByName(String name);
    void createProject(String name);
}

package use_case.get_task;

import entity.Task;

public interface GetTaskDataAccessInterface {
    boolean existsByName(String name);
    Task[] getTasks(String name);
}

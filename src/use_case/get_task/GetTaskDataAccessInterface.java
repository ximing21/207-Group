package use_case.get_task;

import entity.Task;

import java.util.ArrayList;

public interface GetTaskDataAccessInterface {
    boolean existsByName(String name);
    ArrayList<Task> getTasks(String name);
}

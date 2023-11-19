package use_case.add_task;

import entity.Task;

import java.io.IOException;

public interface AddTaskDataAccessInterface {
    String addTask(String taskName, String projectName, String deadline);

}

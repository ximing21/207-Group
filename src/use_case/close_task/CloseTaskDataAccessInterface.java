package use_case.close_task;

import entity.Project;

public interface CloseTaskDataAccessInterface {

    boolean closeTask(String taskId);
}

package use_case.get_task;

import use_case.add_project.AddProjectInputData;

public interface GetTaskInputBoundary {
    void execute(GetTaskInputData getTaskInputData);
}

package interface_adapter.get_task;

import use_case.get_task.GetTaskInputBoundary;
import use_case.get_task.GetTaskInputData;

public class GetTaskController {
    final GetTaskInputBoundary getTaskInteractor;
    public GetTaskController(GetTaskInputBoundary getTaskInteractor) {
        this.getTaskInteractor = getTaskInteractor;
    }

    public void execute(String name) {
        GetTaskInputData getTaskInputData = new GetTaskInputData(name);
        getTaskInteractor.execute(getTaskInputData);
    }
}

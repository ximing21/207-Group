package interface_adapter.close_task;

import use_case.close_task.CloseTaskInputBoundary;
import use_case.close_task.CloseTaskInputData;

public class CloseTaskController {
    final CloseTaskInputBoundary closeTaskInteractor;

    public CloseTaskController(CloseTaskInputBoundary closeTaskInteractor) {
        this.closeTaskInteractor = closeTaskInteractor;
    }

    public void execute(String taskId) {
        CloseTaskInputData closeTaskInputData = new CloseTaskInputData(taskId);
        closeTaskInteractor.execute(closeTaskInputData);
    }
}

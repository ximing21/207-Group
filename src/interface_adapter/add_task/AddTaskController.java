package interface_adapter.add_task;

import use_case.add_task.AddTaskInputBoundary;
import use_case.add_task.AddTaskInputData;



public class AddTaskController {
    final AddTaskInputBoundary addTaskInteractor;
    public AddTaskController(AddTaskInputBoundary addTaskInteractor) {
        this.addTaskInteractor = addTaskInteractor;
    }

    public void execute(String taskName, String deadline, String projectName) {
        AddTaskInputData addTaskInputData = new AddTaskInputData(taskName, projectName, deadline);
        addTaskInteractor.execute(addTaskInputData);
    }
}

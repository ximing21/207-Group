package interface_adapter.add_task;

import use_case.add_project.AddProjectInputBoundary;
import use_case.add_project.AddProjectInputData;
import use_case.add_task.AddTaskInputBoundary;
import use_case.add_task.AddTaskInputData;

public class AddTaskController {
    final AddTaskInputBoundary addTaskInteractor;
    public AddTaskController(AddTaskInputBoundary addTaskInteractor) {
        this.addTaskInteractor = addTaskInteractor;
    }

    public void execute(String name) {
        AddTaskInputData addTaskInputData = new AddTaskInputData(name);

        addTaskInteractor.execute(addTaskInputData);
    }
}

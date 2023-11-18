package use_case.add_task;

import entity.Task;
import use_case.add_project.AddProjectOutputData;

import java.io.IOException;

public class AddTaskInteractor implements AddTaskInputBoundary {
    final AddTaskOutputBoundary presenter;
    final AddTaskDataAccessInterface dataAccessObject;


    public AddTaskInteractor(AddTaskOutputBoundary presenter, AddTaskDataAccessInterface dataAccessObject) {
        this.presenter = presenter;
        this.dataAccessObject = dataAccessObject;
    }


    @Override
    public void execute(AddTaskInputData addTaskInputData) {
        String taskName = addTaskInputData.getTaskName();
        String deadline = addTaskInputData.getDeadline();

        String taskId = dataAccessObject.addTask(addTaskInputData.getTaskName(), addTaskInputData.getProjectName(), deadline);

        AddTaskOutputData addTaskOutputData = new AddTaskOutputData(taskName, taskId, deadline);
        presenter.prepareSuccessView(addTaskOutputData);
    }
}

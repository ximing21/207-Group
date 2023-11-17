package use_case.add_task;

import entity.Task;

public class AddTaskInteractor implements AddTaskInputBoundary {
    final AddTaskOutputBoundary presenter;
    final AddTaskDataAccessInterface dataAccessObject;


    public AddTaskInteractor(AddTaskOutputBoundary presenter, AddTaskDataAccessInterface dataAccessObject) {
        this.presenter = presenter;
        this.dataAccessObject = dataAccessObject;
    }


    @Override
    public void execute(AddTaskInputData addTaskInputData) {
        Task task = Task.builder().build();
        dataAccessObject.addTask(task);

        AddTaskOutputData addTaskOutputData = new AddTaskOutputData(name);
        presenter.prepareSuccessView(addTaskOutputData);
    }
}

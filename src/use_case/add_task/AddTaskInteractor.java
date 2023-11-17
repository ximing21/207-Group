package use_case.add_task;

public class AddTaskInteractor implements AddTaskInputBoundary {
    final AddTaskOutputBoundary presenter;
    final AddTaskDataAccessInterface dataAccessObject;


    public AddTaskInteractor(AddTaskOutputBoundary presenter, AddTaskDataAccessInterface dataAccessObject) {
        this.presenter = presenter;
        this.dataAccessObject = dataAccessObject;
    }


    @Override
    public void execute(AddTaskInputData addTaskInputData) {
        if (dataAccessObject.existsByName(addTaskInputData.getName())) {
            presenter.prepareFailView("Project already exists.");
        } else {
            String name = addTaskInputData.getName();
            dataAccessObject.createProject(name);

            AddTaskOutputData addTaskOutputData = new AddTaskOutputData(name);
            presenter.prepareSuccessView(addTaskOutputData);
        }
    }
}

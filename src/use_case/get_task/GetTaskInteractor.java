package use_case.get_task;

import entity.Task;
import kotlin.Pair;
import use_case.add_project.AddProjectDataAccessInterface;
import use_case.add_project.AddProjectOutputBoundary;
import use_case.add_project.AddProjectOutputData;

import java.util.ArrayList;
import java.util.Map;


public class GetTaskInteractor implements GetTaskInputBoundary{
    final GetTaskOutputBoundary presenter;
    final GetTaskDataAccessInterface dataAccessObject;

    public GetTaskInteractor(GetTaskOutputBoundary presenter, GetTaskDataAccessInterface dataAccessObject) {
        this.presenter = presenter;
        this.dataAccessObject = dataAccessObject;
    }

    @Override
    public void execute(GetTaskInputData getTaskInputData) {
        // If the project name does not exist (this would never be executed based on our design
        if (dataAccessObject.existsByName(getTaskInputData.getName()) == false) {
            presenter.prepareFailView("Project does not exist.");
        } else {
            String name = getTaskInputData.getName();
            Pair<String, ArrayList<Task>> result = dataAccessObject.getTasks(name);
//            ArrayList<Task> tasks= dataAccessObject.getTasks(name);
//            ArrayList<Task> tasks= dataAccessObject.getTasks(name);
            GetTaskOutputData getTaskOutputData = new GetTaskOutputData(result);
            presenter.prepareSuccessView(getTaskOutputData);
        }

    }
}

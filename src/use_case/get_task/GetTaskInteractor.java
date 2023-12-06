package use_case.get_task;

import entity.Task;
import kotlin.Pair;

import java.util.ArrayList;


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
//        if (dataAccessObject.existsByName(getTaskInputData.getName()) == false) {
//            presenter.prepareFailView("Project does not exist.");
//        } else {
            String name = getTaskInputData.getName();
            Pair<String, ArrayList<ArrayList<String>>> result = dataAccessObject.getTasks(name);
            String message = dataAccessObject.getMessage();
            GetTaskOutputData getTaskOutputData = new GetTaskOutputData(result, message);
            presenter.prepareSuccessView(getTaskOutputData);
        }

    }
//}

package use_case.get_task;

import entity.Task;
import kotlin.Pair;

import java.util.ArrayList;

public class GetTaskOutputData {
    private final ArrayList<Task> tasks;
    private final String projectName;
    private final String message;

    public GetTaskOutputData(Pair<String, ArrayList<Task>> result, String message) {
        this.projectName= result.getFirst();
        this.tasks = result.getSecond();
        this.message = message;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getMessage() {return message;}
}

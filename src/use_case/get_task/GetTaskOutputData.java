package use_case.get_task;

import entity.Task;
import kotlin.Pair;

import java.util.ArrayList;

public class GetTaskOutputData {
    private final ArrayList<Task> tasks;
    private final String projectName;

    public GetTaskOutputData(Pair<String, ArrayList<Task>> result) {
        this.projectName= result.getFirst();
        this.tasks = result.getSecond();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String getProjectName() {
        return projectName;
    }
}

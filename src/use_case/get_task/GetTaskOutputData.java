package use_case.get_task;

import entity.Task;
import kotlin.Pair;

import java.util.ArrayList;

public class GetTaskOutputData {
    private final ArrayList<String> taskName;
    private final ArrayList<String> taskId;
    private final ArrayList<String> taskDeadline;

//    private final ArrayList<Task> tasks;
    private final String projectName;
    private final String message;

    //ArrayList<ArrayList<String>>> <Taskname, TaskId, TaskDeadline>
    public GetTaskOutputData(Pair<String, ArrayList<ArrayList<String>>> result, String message) {
        this.taskName = result.getSecond().get(0);
        this.taskId = result.getSecond().get(1);
        this.taskDeadline = result.getSecond().get(2);
        this.projectName= result.getFirst();
//        this.tasks = result.getSecond();
        this.message = message;
    }

//    public ArrayList<Task> getTasks() {
//        return tasks;
//    }


    public String getProjectName() {
        return projectName;
    }

    public String getMessage() {return message;}

    public ArrayList<String> getTaskName() {
        return taskName;
    }

    public ArrayList<String> getTaskId() {
        return taskId;
    }

    public ArrayList<String> getTaskDeadline() {
        return taskDeadline;
    }
}

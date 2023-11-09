package use_case.get_task;

import entity.Task;

public class GetTaskOutputData {
    private final Task[] tasks;

    public GetTaskOutputData(Task[] tasks) {
        this.tasks= tasks;
    }


}

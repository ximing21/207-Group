package interface_adapter.close_task;

import entity.Task;

import java.util.ArrayList;

public class CloseTaskState {
    private int taskId;
    private String phrase;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}
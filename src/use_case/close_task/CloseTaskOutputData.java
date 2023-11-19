package use_case.close_task;

import entity.Project;

public class CloseTaskOutputData {

    private final String phrase;

    public CloseTaskOutputData(String phrase) {
        this.phrase = phrase;

    }

    public String getPhrase() {
        return this.phrase;
    }
}
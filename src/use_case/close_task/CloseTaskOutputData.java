package use_case.close_task;

public class CloseTaskOutputData {
    public boolean is_closed;

    public CloseTaskOutputData(boolean is_closed) {
        this.is_closed = is_closed;
    }

    public boolean getTaskStatus() {
        return is_closed;
    }
    }
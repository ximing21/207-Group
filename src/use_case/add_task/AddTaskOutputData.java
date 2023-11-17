package use_case.add_task;

public class AddTaskOutputData {
    private final String name;

    public AddTaskOutputData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
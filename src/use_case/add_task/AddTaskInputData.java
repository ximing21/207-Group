package use_case.add_task;

public class AddTaskInputData {
    final private String name;

    public AddTaskInputData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

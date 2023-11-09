package use_case.get_task;

public class GetTaskInputData {
    private String name;

    public GetTaskInputData(String project_name) {
        this.name = project_name;
    }

    public String getName() {
        return name;
    }

}

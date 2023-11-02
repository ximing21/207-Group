package interface_adapter.add_project;

public class AddProjectState {
    private String project_name = "";
    private String project_nameError = null;

    public AddProjectState(AddProjectState copy) {
        project_name = copy.project_name;
        project_nameError = copy.project_nameError;
    }

    public AddProjectState() {}

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_nameError() {
        return project_nameError;
    }

    public void setProject_nameError(String project_nameError) {
        this.project_nameError = project_nameError;
    }
}

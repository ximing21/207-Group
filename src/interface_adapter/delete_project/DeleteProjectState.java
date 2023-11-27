package interface_adapter.delete_project;

public class DeleteProjectState {
    private String project_name = "";
    private Integer count = 0;
    private String project_nameError = null;

    public DeleteProjectState(DeleteProjectState copy) {
        project_name = copy.project_name;
        project_nameError = copy.project_nameError;
        count = copy.count;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public DeleteProjectState() {}
    public String getProject_name() {return project_name; }

}

package interface_adapter.delete_project;

public class DeleteProjectState {
    private String project_name = "";

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public DeleteProjectState() {}
    public String getProject_name() {return project_name; }

}

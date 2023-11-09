package interface_adapter.added_project;

public class AddedProjectState {
    private String projectname = "";

    public AddedProjectState(AddedProjectState copy) {projectname = copy.projectname;}
    public AddedProjectState() {}

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }
}

package interface_adapter.add_project;

import use_case.add_project.AddProjectInputBoundary;
import use_case.add_project.AddProjectInputData;

public class AddProjectController {
    final AddProjectInputBoundary addProjectInteractor;
    public AddProjectController(AddProjectInputBoundary addProjectInteractor) {
        this.addProjectInteractor = addProjectInteractor;
    }

    public void execute(String name) {
        AddProjectInputData addProjectInputData = new AddProjectInputData(name);

        addProjectInteractor.execute(addProjectInputData);
    }
}

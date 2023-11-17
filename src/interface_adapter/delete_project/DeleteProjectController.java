package interface_adapter.delete_project;

import use_case.delete_project.DeleteProjectInputBoundary;
import use_case.delete_project.DeleteProjectInputData;

public class DeleteProjectController {
    final DeleteProjectInputBoundary deleteProjectInteractor;
    public DeleteProjectController(DeleteProjectInputBoundary deleteProjectInteractor) {
        this.deleteProjectInteractor = deleteProjectInteractor;
    }

    public void execute(String name) {
        DeleteProjectInputData deleteProjectInputData = new DeleteProjectInputData(name);

        deleteProjectInteractor.execute(deleteProjectInputData);
    }
}

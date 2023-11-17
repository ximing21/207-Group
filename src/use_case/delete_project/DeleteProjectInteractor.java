package use_case.delete_project;


import kotlin.Pair;

public class DeleteProjectInteractor implements DeleteProjectInputBoundary {
    final DeleteProjectOutputBoundary presenter;
    final DeleteProjectDataAccessInterface dataAccessObject;

    public DeleteProjectInteractor(DeleteProjectOutputBoundary presenter, DeleteProjectDataAccessInterface dataAccessObject) {
        this.presenter = presenter;
        this.dataAccessObject = dataAccessObject;
    }

    @Override
    public void execute(DeleteProjectInputData deleteProjectInputData) {
        // if string name != null?
        String name = deleteProjectInputData.getName();
        Integer count = dataAccessObject.deleteProject(name);
        Pair<String,Integer> result = new Pair<>(name, count);
        DeleteProjectOutputData project = new DeleteProjectOutputData(result);
        presenter.prepareSuccessView(project);

    }
}

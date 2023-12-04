package use_case.add_project;

import entity.Project;

public class AddProjectInteractor implements AddProjectInputBoundary {
    final AddProjectOutputBoundary presenter;
    final AddProjectDataAccessInterface dataAccessObject;


    public AddProjectInteractor(AddProjectOutputBoundary presenter, AddProjectDataAccessInterface dataAccessObject) {
        this.presenter = presenter;
        this.dataAccessObject = dataAccessObject;
    }


    @Override
    public void execute(AddProjectInputData addProjectInputData) {
        if (dataAccessObject.existsByName(addProjectInputData.getName())) {
            presenter.prepareFailView("Project already exists.");
        } else {
            String name = addProjectInputData.getName();
            Project project = dataAccessObject.createProject(name);

            AddProjectOutputData addProjectOutputData = new AddProjectOutputData(project.getName());
            presenter.prepareSuccessView(addProjectOutputData);
        }
    }
}

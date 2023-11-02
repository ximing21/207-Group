package use_case.add_project;

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
            dataAccessObject.createProject(name);

            AddProjectOutputData addProjectOutputData = new AddProjectOutputData(name);
            presenter.prepareSuccessView(addProjectOutputData);


        }
    }
}

package use_case.get_all_projects;

import entity.Project;

public class GetProjectInteractor implements GetProjectInputBoundary {
    final GetProjectOutputBoundary presenter;
    final GetProjectDataAccessInterface dataAccessObject;


    public GetProjectInteractor(GetProjectOutputBoundary presenter, GetProjectDataAccessInterface dataAccessObject) {
        this.presenter = presenter;
        this.dataAccessObject = dataAccessObject;
    }


    @Override
    public void execute() {
        Project[] projects = dataAccessObject.getProject();

        GetProjectOutputData getProjectOutputData = new GetProjectOutputData(projects);
        presenter.prepareSuccessView(getProjectOutputData);
        }
    }

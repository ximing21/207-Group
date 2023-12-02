package use_case;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import api.TodoistDB;
import org.junit.Test;
import use_case.add_project.*;
import use_case.delete_project.*;

import static org.junit.Assert.*;

public class DeleteProjectTest {
    private AddProjectDataAccessInterface addProjectDataAccessObject;
    private DeleteProjectDataAccessInterface deleteProjectDataAccessObject;
    private DeleteProjectOutputBoundary successPresenter;

    @Before
    public void setUp() {
        // Mocking the data access objects
        addProjectDataAccessObject = Mockito.mock(AddProjectDataAccessInterface.class);
        deleteProjectDataAccessObject = Mockito.mock(DeleteProjectDataAccessInterface.class);
//        Mockito.doNothing().when(deleteProjectDataAccessObject).deleteProject(Mockito.anyString());

        // Set up success presenter for adding a project
        AddProjectOutputBoundary successAddProjectPresenter = new AddProjectOutputBoundary() {
            @Override
            public void prepareSuccessView(AddProjectOutputData response) {

            }

            @Override
            public void prepareFailView(String error) {
                fail("Adding project for setup failed unexpectedly.");

            }
        };

        AddProjectInputData addProjectInputData = new AddProjectInputData("207");
        AddProjectInputBoundary addProjectInteractor = new AddProjectInteractor(successAddProjectPresenter, addProjectDataAccessObject);
        addProjectInteractor.execute(addProjectInputData);
    }

    @Test
    public void successTest() {
        DeleteProjectOutputBoundary successDeleteProjectPresenter = new DeleteProjectOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteProjectOutputData response) {
                assertEquals("207", response.getProjectName());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");

            }
        };

        DeleteProjectInputData deleteProjectInputData = new DeleteProjectInputData("207");

        DeleteProjectInputBoundary deleteProjectInteractor = new DeleteProjectInteractor(successDeleteProjectPresenter, deleteProjectDataAccessObject);
        deleteProjectInteractor.execute(deleteProjectInputData);

    }

//    @Test
//    public void failedTest() {
//        DeleteProjectDataAccessInterface mockDataAccessObject = Mockito.mock(DeleteProjectDataAccessInterface.class);
//        DeleteProjectOutputBoundary failDeleteProjectPresenter = new DeleteProjectOutputBoundary() {
//            @Override
//            public void prepareSuccessView(DeleteProjectOutputData response) {
//                fail("Use case failure is unexpected.");
//            }
//
//            @Override
//            public void prepareFailView(String error) {
//                assertEquals(error, "Project not found.");
//
//            }
//        };
//        DeleteProjectInputData deleteProjectInputData = new DeleteProjectInputData("NonExistingProjectName");
//        DeleteProjectInputBoundary deleteProjectInteractor = new DeleteProjectInteractor(failDeleteProjectPresenter, mockDataAccessObject);
//        deleteProjectInteractor.execute(deleteProjectInputData);
//    }
}

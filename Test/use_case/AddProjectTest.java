package use_case;

import org.junit.After;
import org.mockito.Mockito;
import api.TodoistDB;
import org.junit.Test;
import use_case.add_project.*;
import use_case.delete_project.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AddProjectTest {

    private AddProjectOutputBoundary presenter;
    private AddProjectDataAccessInterface dataAccessObject;
    private AddProjectInteractor addProjectInteractor;

    @Before
    public void setUp() {
        presenter = Mockito.mock(AddProjectOutputBoundary.class);
        dataAccessObject = Mockito.mock(AddProjectDataAccessInterface.class);
        addProjectInteractor = new AddProjectInteractor(presenter, dataAccessObject);
    }

    @Test
    public void successTest() {
        String projectName = "New Project";
        when(dataAccessObject.existsByName(projectName)).thenReturn(false);

        AddProjectInputData inputData = new AddProjectInputData(projectName);
        addProjectInteractor.execute(inputData);

        verify(dataAccessObject).createProject(projectName);
        verify(presenter).prepareSuccessView(any(AddProjectOutputData.class));
    }

    @Test
    public void failTest() {
        String projectName = "Existing Project";
        when(dataAccessObject.existsByName(projectName)).thenReturn(true);

        AddProjectInputData inputData = new AddProjectInputData(projectName);
        addProjectInteractor.execute(inputData);

        verify(dataAccessObject, never()).createProject(projectName);
        verify(presenter).prepareFailView("Project already exists.");
    }
}

//public class AddProjectTest {
//
//        @Test
//        public void successTest() {
//            AddProjectDataAccessInterface mockDAO = Mockito.mock(AddProjectDataAccessInterface.class);
//            AddProjectOutputBoundary successPresenter = new AddProjectOutputBoundary() {
//                @Override
//                public void prepareSuccessView(AddProjectOutputData response) {
//                    assertEquals("207", response.getName());
//                }
//                @Override
//                public void prepareFailView(String error) {
//                    fail("Use case failure is unexpected.");
//                }
//            };
//            AddProjectInputData inputData = new AddProjectInputData("207");
//            AddProjectInputBoundary interactor = new AddProjectInteractor(successPresenter,mockDAO);
//            interactor.execute(inputData);
//        }
//
//
//        @Test
//        public void failedTest() {
//            AddProjectDataAccessInterface dataAccessInterface = new TodoistDB();
//            DeleteProjectDataAccessInterface deleteDAO = new TodoistDB();
//            try {
//                AddProjectOutputBoundary presenter = new AddProjectOutputBoundary() {
//                    @Override
//                    public void prepareSuccessView(AddProjectOutputData response) {
//                        fail();
//                    }
//                    @Override
//                    public void prepareFailView(String error) {
//                    }
//                };
//                AddProjectInputData inputData = new AddProjectInputData("207");
//                AddProjectInputBoundary interactor = new AddProjectInteractor(presenter, dataAccessInterface);
//                interactor.execute(inputData);
//            } catch (java.lang.AssertionError assertionError) {
//                AddProjectOutputBoundary failPresenter = new AddProjectOutputBoundary() {
//                    @Override
//                    public void prepareSuccessView(AddProjectOutputData response) {
//                        fail("Use case failure is unexpected.");
//                    }
//                    @Override
//                    public void prepareFailView(String error) {
//                        assertEquals(error, "Project already exists.");
//                    }
//                };
//
//                AddProjectInputData inputData = new AddProjectInputData("207");
//                AddProjectInputBoundary interactor = new AddProjectInteractor(failPresenter, dataAccessInterface);
//                interactor.execute(inputData);
//
//                // clean up the database
//                DeleteProjectInputData deleteProjectInputData = new DeleteProjectInputData("207");
//                DeleteProjectOutputBoundary deletePresenter = new DeleteProjectOutputBoundary() {
//                    @Override
//                    public void prepareSuccessView(DeleteProjectOutputData response) {
//                    }
//
//                    @Override
//                    public void prepareFailView(String error) {
//
//                    }
//                };
//                DeleteProjectInteractor deleteInteractor = new DeleteProjectInteractor(deletePresenter, deleteDAO);
//                deleteInteractor.execute(deleteProjectInputData);
//
//            }
//        }
//}








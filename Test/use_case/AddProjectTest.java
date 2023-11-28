package use_case;

import org.mockito.Mockito;
import api.TodoistDB;
import org.junit.Test;
import use_case.add_project.*;
import use_case.delete_project.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class AddProjectTest {

        @Test
        public void successTest() {
            AddProjectDataAccessInterface mockDAO = Mockito.mock(AddProjectDataAccessInterface.class);
            AddProjectOutputBoundary successPresenter = new AddProjectOutputBoundary() {
                @Override
                public void prepareSuccessView(AddProjectOutputData response) {
                    assertEquals("207", response.getName());
                }
                @Override
                public void prepareFailView(String error) {
                    fail("Use case failure is unexpected.");
                }
            };
            AddProjectInputData inputData = new AddProjectInputData("207");
            AddProjectInputBoundary interactor = new AddProjectInteractor(successPresenter,mockDAO);
            interactor.execute(inputData);
        }


        @Test
        public void failedTest() {
            AddProjectDataAccessInterface dataAccessInterface = new TodoistDB();
            DeleteProjectDataAccessInterface deleteDAO = new TodoistDB();
            try {
                AddProjectOutputBoundary presenter = new AddProjectOutputBoundary() {
                    @Override
                    public void prepareSuccessView(AddProjectOutputData response) {
                        fail();
                    }
                    @Override
                    public void prepareFailView(String error) {
                    }
                };
                AddProjectInputData inputData = new AddProjectInputData("207");
                AddProjectInputBoundary interactor = new AddProjectInteractor(presenter, dataAccessInterface);
                interactor.execute(inputData);
            } catch (java.lang.AssertionError assertionError) {
                AddProjectOutputBoundary failPresenter = new AddProjectOutputBoundary() {
                    @Override
                    public void prepareSuccessView(AddProjectOutputData response) {
                        fail("Use case failure is unexpected.");
                    }
                    @Override
                    public void prepareFailView(String error) {
                        assertEquals(error, "Project already exists.");
                    }
                };
                AddProjectInputData inputData = new AddProjectInputData("207");
                AddProjectInputBoundary interactor = new AddProjectInteractor(failPresenter, dataAccessInterface);
                interactor.execute(inputData);
                DeleteProjectInputData deleteProjectInputData = new DeleteProjectInputData("207");
                DeleteProjectOutputBoundary deletePresenter = new DeleteProjectOutputBoundary() {
                    @Override
                    public void prepareSuccessView(DeleteProjectOutputData response) {
                    }

                    @Override
                    public void prepareFailView(String error) {

                    }
                };
                DeleteProjectInteractor deleteInteractor = new DeleteProjectInteractor(deletePresenter, deleteDAO);
                deleteInteractor.execute(deleteProjectInputData);

            }



//            AddProjectInputData inputData = new AddProjectInputData("207");
//            AddProjectInputBoundary interactor = new AddProjectInteractor(failPresenter, mockDAO);
//            interactor.execute(inputData);
//            try{
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
//                AddProjectInputData inputData = new AddProjectInputData("207");
//                AddProjectInputBoundary interactor = new AddProjectInteractor(failPresenter, mockDAO);
//                interactor.execute(inputData);
//
//            } catch (java.lang.AssertionError assertionError) {
//                interactor.execute(inputData);

            }
//            AddProjectInputBoundary interactor = Mockito.mock(AddProjectInputBoundary.class);
//            Mockito.doNothing().when(interactor).execute(inputData);
//            interactor.execute(inputData);
//            interactor.execute(inputData);
        }








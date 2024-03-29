package use_case;


import api.TodoistDB;
import entity.Project;
import entity.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import use_case.add_project.AddProjectDataAccessInterface;
import use_case.add_project.AddProjectOutputBoundary;
import use_case.add_project.AddProjectOutputData;
import use_case.add_task.*;
import use_case.close_task.CloseTaskDataAccessInterface;
import use_case.close_task.CloseTaskInputData;
import use_case.delete_project.*;
import use_case.get_all_projects.GetProjectDataAccessInterface;
import use_case.get_all_projects.GetProjectInputBoundary;
import use_case.get_all_projects.GetProjectInteractor;
import use_case.get_task.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GetTaskTest {
    private AddTaskDataAccessInterface addTaskDataAccessInterface;
    private GetTaskDataAccessInterface getTaskDataAccessInterface;
    private AddProjectDataAccessInterface addProjectDataAccessInterface;
    private GetProjectDataAccessInterface getProjectDataAccessInterface;
    private GetTaskOutputBoundary successPresenter;
//    private List<Task> taskList;
//    private List<Project> projectList;


    @Test
    public void successTest(){
        AddTaskDataAccessInterface addTaskDataAccessInterface = new TodoistDB();
        AddProjectDataAccessInterface addProjectDataAccessInterface = new TodoistDB();
        GetTaskDataAccessInterface getTaskDataAccessInterface = new TodoistDB();
        GetProjectDataAccessInterface getProjectDataAccessInterface = new TodoistDB();

        // add a new project
        addProjectDataAccessInterface.createProject("207");
        addTaskDataAccessInterface.addTask("final", "207", "2023-12-15");

        GetTaskOutputBoundary successPresenter = new GetTaskOutputBoundary() {

            @Override
            public void prepareSuccessView(GetTaskOutputData response) {
                // recall: result type is Pair<String, ArrayList<Task>>
                // String is the project name
                assertEquals("207", response.getProjectName());
                assertEquals("final", response.getTasks().get(0).getTaskName());
                assertEquals("2023-12-15", response.getTasks().get(0).getDeadline());
                assertEquals(1, response.getTasks().size());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected");
            }
        };

        GetTaskInputData inputData = new GetTaskInputData("207");
        GetTaskInputBoundary interactor = new GetTaskInteractor(successPresenter, getTaskDataAccessInterface);
        interactor.execute(inputData);
    }


    // clean up the database
    @After
    public void cleanUp() {
        DeleteProjectDataAccessInterface deleteProjectDataAccessInterface = new TodoistDB();
//        GetTaskDataAccessInterface getTaskDataAccessInterface = new TodoistDB();
//
//        GetTaskInputData getTaskInputData = new GetTaskInputData("207");
//        GetTaskInputBoundary interactor = new GetTaskInteractor(successPresenter, getTaskDataAccessInterface);
//        interactor.execute(getTaskInputData);

        DeleteProjectInputData deleteProjectInputData = new DeleteProjectInputData("207");
        DeleteProjectOutputBoundary deletePresenter = new DeleteProjectOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteProjectOutputData response) {
            }

            @Override
            public void prepareFailView(String error) {

            }
        };
        DeleteProjectInteractor deleteInteractor =
                new DeleteProjectInteractor(deletePresenter, deleteProjectDataAccessInterface);
        deleteInteractor.execute(deleteProjectInputData);

    }
}


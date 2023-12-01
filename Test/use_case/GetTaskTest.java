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


//    public void setUp() {
//        addTaskDataAccessInterface = Mockito.mock(AddTaskDataAccessInterface.class);
//        getTaskDataAccessInterface = Mockito.mock(GetTaskDataAccessInterface.class);
//        addProjectDataAccessInterface = Mockito.mock(AddProjectDataAccessInterface.class);
//        getProjectDataAccessInterface = Mockito.mock(GetProjectDataAccessInterface.class);
//        taskList = new ArrayList<>();
//        projectList = new ArrayList<>();
//
//        Mockito.doAnswer(invocation -> {
//            String projectName = invocation.getArgument(0);
//            projectList.add(new Project("test_id", projectName, 0));
//            return null;
//        }).when(addProjectDataAccessInterface).createProject(Mockito.anyString());
//
//        Mockito.when(getProjectDataAccessInterface.getProject()).thenAnswer(invocation ->
//                projectList.toArray(new Project[0])
//        );
//
//
//        Mockito.doAnswer(invocation -> {
//            String taskName = invocation.getArgument(0);
//            String deadline = invocation.getArgument(2);
//            taskList.add(new Task("test_id", taskName,"test_id", deadline));
//            return null;
//        }).when(addTaskDataAccessInterface).addTask(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
//
////        Mockito.when(getTaskDataAccessInterface.getTasks(Mockito.anyString())).
////                thenAnswer(invocation -> new Pair<String name ="207",taskList.toArray(new Task[0])>);
//
//
//        successPresenter = new GetTaskOutputBoundary() {
//
//            @Override
//            public void prepareSuccessView(GetTaskOutputData response) {
//                assertEquals("207", response.getProjectName());
//                assertEquals("New Task", response.getTasks().get(0).getTaskName());
//                assertEquals("2023-12-11", response.getTasks().get(0).getDeadline());
//                assertEquals(1, response.getTasks().size());
//                assertTrue("taskList contains 'New Task'",
//                        Arrays.stream(new Task[]{response.getTasks().get(0)})
//                                .anyMatch(p -> "New Task".equals(p.getTaskName())));
//            }
//
//            @Override
//            public void prepareFailView(String error) {
//                fail("Use case failure is unexpected");
//            }
//        };
//    }
//
//
//    @Test
//    public void successTest() {
//        // Mock behavior for createProject method
//        Mockito.doNothing().when(addProjectDataAccessInterface).createProject("207");
//
//        // Mock behavior for getProject method
//        Project[] mockedProjects = new Project[]{new Project("MockedProjectId", "MockedProjectName", 0)};
//        Mockito.when(getProjectDataAccessInterface.getProject()).thenReturn(mockedProjects);
//
//        // Execute the lines
//        addProjectDataAccessInterface.createProject("207");
//        Project project = getProjectDataAccessInterface.getProject()[0];
//
//        // Add new task
//        addTaskDataAccessInterface.addTask("New Task", project.getName(), "2023-12-11" );
//
//        GetTaskInputBoundary interactor = new GetTaskInteractor(successPresenter, getTaskDataAccessInterface);
//        GetTaskInputData projectName = new GetTaskInputData("207");
//        interactor.execute(projectName);
//    }
//}

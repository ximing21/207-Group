package use_case;


import entity.Project;
import entity.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import use_case.add_project.AddProjectDataAccessInterface;
import use_case.add_task.AddTaskDataAccessInterface;
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
    private List<Task> taskList;
    private List<Project> projectList;

    @Before
    public void setUp() {
        addTaskDataAccessInterface = Mockito.mock(AddTaskDataAccessInterface.class);
        getTaskDataAccessInterface = Mockito.mock(GetTaskDataAccessInterface.class);
        addProjectDataAccessInterface = Mockito.mock(AddProjectDataAccessInterface.class);
        getProjectDataAccessInterface = Mockito.mock(GetProjectDataAccessInterface.class);
        taskList = new ArrayList<>();
        projectList = new ArrayList<>();

        Mockito.doAnswer(invocation -> {
            String projectName = invocation.getArgument(0);
            projectList.add(new Project("test_id", projectName, 0));
            return null;
        }).when(addProjectDataAccessInterface).createProject(Mockito.anyString());

        Mockito.when(getProjectDataAccessInterface.getProject()).thenAnswer(invocation ->
                projectList.toArray(new Project[0])
        );


        Mockito.doAnswer(invocation -> {
            String taskName = invocation.getArgument(0);
            String deadline = invocation.getArgument(2);
            taskList.add(new Task("test_id", taskName,"test_id", deadline));
            return null;
        }).when(addTaskDataAccessInterface).addTask(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

//        Mockito.when(getTaskDataAccessInterface.getTasks(Mockito.anyString())).
//                thenAnswer(invocation -> new Pair<String name ="207",taskList.toArray(new Task[0])>);


        successPresenter = new GetTaskOutputBoundary() {

            @Override
            public void prepareSuccessView(GetTaskOutputData response) {
                assertEquals("207", response.getProjectName());
                assertEquals("New Task", response.getTasks().get(0).getTaskName());
                assertEquals("2023-12-11", response.getTasks().get(0).getDeadline());
                assertEquals(1, response.getTasks().size());
                assertTrue("taskList contains 'New Task'",
                        Arrays.stream(new Task[]{response.getTasks().get(0)})
                                .anyMatch(p -> "New Task".equals(p.getTaskName())));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected");
            }
        };
    }


    @Test
    public void successTest() {
        // Mock behavior for createProject method
        Mockito.doNothing().when(addProjectDataAccessInterface).createProject("207");

        // Mock behavior for getProject method
        Project[] mockedProjects = new Project[]{new Project("MockedProjectId", "MockedProjectName", 0)};
        Mockito.when(getProjectDataAccessInterface.getProject()).thenReturn(mockedProjects);

        // Execute the lines
        addProjectDataAccessInterface.createProject("207");
        Project project = getProjectDataAccessInterface.getProject()[0];

        // Add new task
        addTaskDataAccessInterface.addTask("New Task", project.getName(), "2023-12-11" );

        GetTaskInputBoundary interactor = new GetTaskInteractor(successPresenter, getTaskDataAccessInterface);
        GetTaskInputData projectName = new GetTaskInputData("207");
        interactor.execute(projectName);
    }
}

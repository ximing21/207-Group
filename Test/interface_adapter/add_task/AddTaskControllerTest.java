package interface_adapter.add_task;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.add_task.AddTaskInputBoundary;
import use_case.add_task.AddTaskInputData;

public class AddTaskControllerTest {

    private AddTaskInputBoundary mockInteractor;
    private AddTaskController controller;

    @Before
    public void setUp() {
        mockInteractor = Mockito.mock(AddTaskInputBoundary.class);
        controller = new AddTaskController(mockInteractor);
    }

    @Test
    public void testExecute() {
        String taskName = "Task";
        String deadline = "2023-12-15";
        String projectName = "Project";

        controller.execute(taskName, deadline, projectName);

        Mockito.verify(mockInteractor).execute(Mockito.any(AddTaskInputData.class));
    }
}

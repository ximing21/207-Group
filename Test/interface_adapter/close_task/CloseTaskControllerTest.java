package interface_adapter.close_task;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.close_task.CloseTaskInputBoundary;
import use_case.close_task.CloseTaskInputData;

public class CloseTaskControllerTest {

    private CloseTaskInputBoundary mockInteractor;
    private CloseTaskController controller;

    @Before
    public void setUp() {
        mockInteractor = Mockito.mock(CloseTaskInputBoundary.class);
        controller = new CloseTaskController(mockInteractor);
    }

    @Test
    public void testExecute() {
        String taskId = "taskId123";

        controller.execute(taskId);

        Mockito.verify(mockInteractor).execute(Mockito.any(CloseTaskInputData.class));
    }
}

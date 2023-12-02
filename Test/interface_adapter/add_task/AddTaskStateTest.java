package interface_adapter.add_task;

import interface_adapter.ViewManagerModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.add_task.AddTaskOutputData;

import static junit.framework.TestCase.assertEquals;

public class AddTaskStateTest {

    @Test
    public void testGettersAndSetters() {
        AddTaskState state = new AddTaskState();
        state.setProject_name("Project");
        state.setTask_name("Task");
        state.setTaskId("taskId");
        state.setTaskDeadline("2023-12-15");

        assertEquals("Project", state.getProject_name());
        assertEquals("Task", state.getTask_name());
        assertEquals("taskId", state.getTaskId());
        assertEquals("2023-12-15", state.getTaskDeadline());
    }

    @Test
    public void testProjectNameGetAndSet() {
        AddTaskState state = new AddTaskState();
        String expectedProjectName = "Example Project";

        // Set the project name
        state.setProject_name(expectedProjectName);

        // Retrieve the project name
        String actualProjectName = state.getProject_name();

        // Assert that the retrieved project name is as expected
        assertEquals("The project name should match the set value", expectedProjectName, actualProjectName);
    }
}

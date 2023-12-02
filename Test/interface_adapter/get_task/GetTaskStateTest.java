package interface_adapter.get_task;

import entity.Task;
import interface_adapter.ViewManagerModel;
import kotlin.Pair;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.get_task.GetTaskOutputData;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class GetTaskStateTest {

    @Test
    public void testGetAndSetTasks() {
        GetTaskState state = new GetTaskState();
        ArrayList<Task> expectedTasks = new ArrayList<>();

        state.setTasks(expectedTasks);
        ArrayList<Task> actualTasks = state.getTasks();

        assertEquals("The tasks should match the set value", expectedTasks, actualTasks);
    }

    @Test
    public void testGetAndSetMessage() {
        GetTaskState state = new GetTaskState();
        String expectedMessage = "Test Message";

        state.setMessage(expectedMessage);
        String actualMessage = state.getMessage();

        assertEquals("The message should match the set value", expectedMessage, actualMessage);
    }
}


package interface_adapter.close_task;

import interface_adapter.ViewManagerModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.close_task.CloseTaskOutputData;

import static junit.framework.TestCase.assertNotNull;

public class CloseTaskStateTest {

    @Test
    public void testConstructor() {
        CloseTaskState state = new CloseTaskState();
        assertNotNull("State should not be null", state);
    }
}

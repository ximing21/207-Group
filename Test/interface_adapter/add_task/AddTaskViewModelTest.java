package interface_adapter.add_task;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static junit.framework.TestCase.assertEquals;

public class AddTaskViewModelTest {

    private AddTaskViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new AddTaskViewModel();
    }

    @Test
    public void testStateManagement() {
        AddTaskState state = new AddTaskState();
        state.setTask_name("Task");

        viewModel.setState(state);
        AddTaskState retrievedState = viewModel.getState();

        assertEquals("Task", retrievedState.getTask_name());
    }

    @Test
    public void testPropertyChangeListener() {
        PropertyChangeListener mockListener = Mockito.mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);

        viewModel.firePropertyChanged();

        Mockito.verify(mockListener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }
}

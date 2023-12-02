package interface_adapter.close_task;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class CloseTaskViewModelTest {

    private CloseTaskViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new CloseTaskViewModel();
    }

    @Test
    public void testStateManagement() {
        CloseTaskState state = new CloseTaskState();

        viewModel.setState(state);

        assertEquals("State should be set correctly", state, viewModel.getState());
    }

    @Test
    public void testPropertyChangeListener() {
        PropertyChangeListener mockListener = Mockito.mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);

        viewModel.firePropertyChanged();

        Mockito.verify(mockListener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }
}

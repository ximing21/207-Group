package interface_adapter.get_task;

import entity.Task;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GetTaskViewModelTest {

    private GetTaskViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new GetTaskViewModel();
    }

    @Test
    public void testStateManagement() {
        GetTaskState state = new GetTaskState();

        viewModel.setState(state);

        assertEquals("State should be set correctly", state, viewModel.getState());
    }

    @Test
    public void testTitleLabelChange() {
        String oldTitle = viewModel.getTitleLabel();
        String newTitle = "New Title";

        viewModel.setTitleLabel(newTitle);

        assertEquals("Title label should be updated", newTitle, viewModel.getTitleLabel());
        assertNotEquals("Old and new titles should be different", oldTitle, newTitle);
    }

    @Test
    public void testPropertyChangeListener() {
        PropertyChangeListener mockListener = Mockito.mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);

        viewModel.firePropertyChanged();

        Mockito.verify(mockListener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }
}

package interface_adapter.delete_project;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static junit.framework.TestCase.assertEquals;

public class DeleteProjectViewModelTest {

    private DeleteProjectViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new DeleteProjectViewModel();
    }

    @Test
    public void testStateManagement() {
        DeleteProjectState state = new DeleteProjectState();
        state.setProject_name("Test Project");

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

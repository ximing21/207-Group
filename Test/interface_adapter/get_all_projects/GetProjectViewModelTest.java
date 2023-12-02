package interface_adapter.get_all_projects;

import entity.Project;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class GetProjectViewModelTest {

    private GetProjectViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new GetProjectViewModel();
    }

    @Test
    public void testStateManagement() {
        GetProjectState state = new GetProjectState();

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

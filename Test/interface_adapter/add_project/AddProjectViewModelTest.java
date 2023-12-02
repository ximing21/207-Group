package interface_adapter.add_project;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static junit.framework.TestCase.assertEquals;

public class AddProjectViewModelTest {

    private AddProjectViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new AddProjectViewModel();
    }

    @Test
    public void testStateManagement() {
        AddProjectState state = new AddProjectState();

        viewModel.setState(state);
        assertEquals(state, viewModel.getState());
    }

    @Test
    public void testPropertyChangeListener() {
        PropertyChangeListener mockListener = Mockito.mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);

        viewModel.firePropertyChanged();

        Mockito.verify(mockListener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }
}

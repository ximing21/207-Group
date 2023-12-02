package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViewModelTest {

    private static class TestViewModel extends ViewModel {
        public TestViewModel(String viewName) {
            super(viewName);
        }

        @Override
        public void firePropertyChanged() {
            // Simple implementation or mock
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {
            // Simple implementation or mock
        }
    }

    private ViewModel testViewModel;

    @Before
    public void setUp() {
        testViewModel = new TestViewModel("TestView");
    }

    @Test
    public void testGetViewName() {
        assertEquals("TestView", testViewModel.getViewName());
    }

    // Additional tests for the abstract methods if necessary
}

package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_project.AddProjectViewModel;
import interface_adapter.switch_view.SwitchViewPresenter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.switch_view.SwitchViewInteractor;
import use_case.switch_view.SwitchViewOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.spy;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.beans.PropertyChangeListener;

public class ViewManagerModelTest {
    private ViewManagerModel model;
    private PropertyChangeListener listener;

    @Before
    public void setUp() {
        model = new ViewManagerModel();
        listener = mock(PropertyChangeListener.class);
    }

    @Test
    public void testGetActiveView() {
        model.setActiveView("View1");
        assertEquals("View1", model.getActiveView());
    }

    @Test
    public void testSetActiveView() {
        model.setActiveView("View2");
        assertEquals("View2", model.getActiveView());
    }

    @Test
    public void testFirePropertyChanged() {
        model.addPropertyChangeListener(listener);
        model.setActiveView("View3");
        model.firePropertyChanged();
        verify(listener).propertyChange(any());
    }

    @Test
    public void testAddPropertyChangeListener() {
        model.addPropertyChangeListener(listener);
        // Further verification if necessary
    }

    // Additional tests for null values and edge cases
}


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

public class ViewManagerTest {

    private CardLayout cardLayout;
    private JPanel views;
    private ViewManagerModel viewManagerModel;
    private ViewManager viewManager;

    @Before
    public void setUp() {
        cardLayout = spy(new CardLayout());
        views = new JPanel(cardLayout);
        viewManagerModel = mock(ViewManagerModel.class);

        viewManager = new ViewManager(views, cardLayout, viewManagerModel);

        ArgumentCaptor<PropertyChangeListener> captor = ArgumentCaptor.forClass(PropertyChangeListener.class);
        verify(viewManagerModel).addPropertyChangeListener(captor.capture());
        PropertyChangeListener listener = captor.getValue();

        listener.propertyChange(new PropertyChangeEvent(viewManagerModel, "view", "", "SomeView"));
    }

    @Test
    public void testViewSwitch() {

        verify(cardLayout).show(views, "SomeView");
    }
}


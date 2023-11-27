package view;

import entity.Task;
import interface_adapter.add_task.AddTaskController;
import interface_adapter.add_task.AddTaskState;
import interface_adapter.add_task.AddTaskViewModel;
import interface_adapter.close_task.CloseTaskController;
import interface_adapter.get_task.GetTaskState;
import interface_adapter.get_task.GetTaskViewModel;
import interface_adapter.switch_view.SwitchViewController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import view.GetTaskView;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GetTaskViewTest {

    private GetTaskViewModel getTaskViewModel;
    private SwitchViewController switchViewController;
    private CloseTaskController closeTaskController;
    private AddTaskController addTaskController;
    private AddTaskViewModel addTaskViewModel;
    private GetTaskView getTaskView;

    @Before
    public void setUp() {
        getTaskViewModel = mock(GetTaskViewModel.class);
        switchViewController = mock(SwitchViewController.class);
        closeTaskController = mock(CloseTaskController.class);
        addTaskController = mock(AddTaskController.class);
        addTaskViewModel = mock(AddTaskViewModel.class);

        getTaskView = new GetTaskView(getTaskViewModel, switchViewController, closeTaskController, addTaskController, addTaskViewModel);
    }

    @Test
    public void testActionPerformed() {
        // TODO: Write test case for actionPerformed method
    }


    @Test
    public void testPropertyChange_AddTaskState() {
        AddTaskState addTaskState = mock(AddTaskState.class);
        when(addTaskState.getTask_name()).thenReturn("New Task");
        when(addTaskState.getTaskDeadline()).thenReturn("2022-12-31");
        when(addTaskState.getTaskId()).thenReturn("123");

        when(getTaskViewModel.getTitleLabel()).thenReturn("Project Title");

        getTaskView.propertyChange(new PropertyChangeEvent(this, "state", null, addTaskState));

        assertEquals(1, getTaskView.tasksArea.getComponentCount());
        JCheckBox checkBox = (JCheckBox) getTaskView.tasksArea.getComponent(0);
        assertEquals("New Task (Deadline: 2022-12-31)", checkBox.getText());
    }

    @Test
    public void testHandleCheckBoxAction_selected() {
        JCheckBox checkBox = mock(JCheckBox.class);
        when(checkBox.isSelected()).thenReturn(true);
        when(checkBox.isEnabled()).thenReturn(true);

        getTaskView.handleCheckBoxAction("123", checkBox);

        verify(closeTaskController).execute("123");
        verify(checkBox).setEnabled(false);
    }

}
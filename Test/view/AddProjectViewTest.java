package view;

import interface_adapter.add_project.AddProjectController;
import interface_adapter.add_project.AddProjectState;
import interface_adapter.add_project.AddProjectViewModel;
import interface_adapter.added_project.AddedProjectState;
import interface_adapter.added_project.AddedProjectViewModel;
import interface_adapter.delete_project.DeleteProjectController;
import interface_adapter.delete_project.DeleteProjectState;
import interface_adapter.delete_project.DeleteProjectViewModel;
import interface_adapter.get_all_projects.GetProjectController;
import interface_adapter.get_all_projects.GetProjectViewModel;
import interface_adapter.get_task.GetTaskController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import view.AddProjectView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AddProjectViewTest {

    private AddProjectViewModel addProjectViewModel;
    private AddProjectController addProjectController;
    private AddedProjectViewModel addedProjectViewModel;
    private GetProjectViewModel getProjectViewModel;
    private GetProjectController getProjectController;
    private GetTaskController getTaskController;
    private DeleteProjectController deleteProjectController;
    private DeleteProjectViewModel deleteProjectViewModel;
    private AddProjectView addProjectView;

    @Before
    public void setUp() {
        addProjectViewModel = mock(AddProjectViewModel.class);
        addProjectController = mock(AddProjectController.class);
        addedProjectViewModel = mock(AddedProjectViewModel.class);
        getProjectViewModel = mock(GetProjectViewModel.class);
        getProjectController = mock(GetProjectController.class);
        getTaskController = mock(GetTaskController.class);
        deleteProjectController = mock(DeleteProjectController.class);
        deleteProjectViewModel = mock(DeleteProjectViewModel.class);

        addProjectView = new AddProjectView(addProjectViewModel, addProjectController, addedProjectViewModel,
                getProjectViewModel, getProjectController, getTaskController, deleteProjectController,
                deleteProjectViewModel);
    }

    @Test
    public void testActionPerformed() {
        // Test that actionPerformed method does not throw any exceptions
        addProjectView.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "command"));
    }
}


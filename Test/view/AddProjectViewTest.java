package view;

import app.Main;
import interface_adapter.add_project.AddProjectController;
import interface_adapter.add_project.AddProjectViewModel;
import interface_adapter.added_project.AddedProjectViewModel;
import interface_adapter.delete_project.DeleteProjectController;
import interface_adapter.delete_project.DeleteProjectViewModel;
import interface_adapter.get_all_projects.GetProjectController;
import interface_adapter.get_all_projects.GetProjectViewModel;
import interface_adapter.get_task.GetTaskController;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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

    public JButton getAddProjectButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        AddProjectView addProjectView1 = (AddProjectView) jp2.getComponent(0);

        JPanel inputPanel = (JPanel) addProjectView1.getComponent(1);

        return (JButton) inputPanel.getComponent(0); // this should be the add project button
    }
    public JButton getAllProjectButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        AddProjectView addProjectView1 = (AddProjectView) jp2.getComponent(0);

        JPanel buttons = (JPanel) addProjectView1.getComponent(3);

        return (JButton) buttons.getComponent(0); // this should be the get project button
    }


    @org.junit.Test
    public void testAllProjectButtonPresent() {
        Main.main(null);
        JButton button = getAllProjectButton();
        assert(button.getText().equals("Get All Projects"));
    }

    @org.junit.Test
    public void testAddProjectButtonPresent() {
        Main.main(null);
        JButton button = getAllProjectButton();
        assert(button.getText().equals("Get All Projects"));
    }
}


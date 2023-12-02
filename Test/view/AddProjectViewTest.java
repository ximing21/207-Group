package view;

import app.Main;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_project.AddProjectController;
import interface_adapter.add_project.AddProjectPresenter;
import interface_adapter.add_project.AddProjectState;
import interface_adapter.add_project.AddProjectViewModel;
import interface_adapter.add_task.AddTaskPresenter;
import interface_adapter.add_task.AddTaskState;
import interface_adapter.add_task.AddTaskViewModel;
import interface_adapter.added_project.AddedProjectState;
import interface_adapter.added_project.AddedProjectViewModel;
import interface_adapter.delete_project.DeleteProjectController;
import interface_adapter.delete_project.DeleteProjectViewModel;
import interface_adapter.get_all_projects.GetProjectController;
import interface_adapter.get_all_projects.GetProjectViewModel;
import interface_adapter.get_task.GetTaskController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.add_project.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
    static String message = "";
    static boolean popUpDiscovered = false;
    private java.util.List<String> projectList;
    private AddProjectViewModel mockViewModel;
    private ViewManagerModel mockViewManagerModel;
    private AddProjectPresenter presenter;
    private AddProjectState mockState;
    private AddedProjectViewModel mockAddedProjectViewModel;

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

        return (JButton) inputPanel.getComponent(1); // this should be the add project button
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
        JButton button = getAddProjectButton();
        assert(button.getText().equals("Add Project"));
    }

    private Timer createCloseTimer() {
        ActionListener close = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Window[] windows = Window.getWindows();
                for (Window window : windows) {

                    if (window instanceof JDialog) {

                        JDialog dialog = (JDialog)window;

                        // this ignores old dialogs
                        if (dialog.isVisible()) {
                            String s = ((JOptionPane) ((BorderLayout) dialog.getRootPane()
                                    .getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER)).getMessage().toString();
                            System.out.println("message = " + s);

                            // store the information we got from the JDialog
                            AddProjectViewTest.message = s;
                            AddProjectViewTest.popUpDiscovered = true;

                            System.out.println("disposing of..." + window.getClass());
                            window.dispose();
                        }
                    }
                }
            }

        };

        Timer t = new Timer(1000, close);
        t.setRepeats(false);
        return t;
    }

    public void addProject() {
        mockViewModel = Mockito.mock(AddProjectViewModel.class);
        mockAddedProjectViewModel = Mockito.mock(AddedProjectViewModel.class);
        mockViewManagerModel = Mockito.mock(ViewManagerModel.class);
        mockState = Mockito.mock(AddProjectState.class);

        Mockito.when(mockViewModel.getState()).thenReturn(mockState);

        presenter = new AddProjectPresenter(mockViewModel, mockAddedProjectViewModel, mockViewManagerModel);
    }

    @org.junit.Test
    public void testAddProjectPopUpShown() {

        addProject();
        popUpDiscovered = false;

        Main.main(null);
        JFrame app = null;

        JButton button = getAddProjectButton();


        // since clicking the button should end up displaying a JDialog to the user to report the
        // result, we set a timer, which will execute code necessary to complete the testing.
        createCloseTimer().start();

        //click the button
        button.doClick();

        // will continue execution here after the JDialog is closed

        // confirm a popUp was discovered
        assert(popUpDiscovered);
        System.out.println("popup was detected successfully.");
    }

    @org.junit.Test
    public void testProjectAdded() {

        addProject();
        Main.main(null);
        JButton button = getAddProjectButton();

        // since clicking the button should end up displaying a JDialog to the user to report the
        // result, we set a timer, which will execute code necessary to complete the testing.
        createCloseTimer().start();

        button.doClick();

        // will continue execution here after the JDialog is closed
//        assertEquals(mockState, new AddProjectState());



    }
}


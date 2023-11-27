package app;

import api.TodoistDB;
import app.AddProjectUseCaseFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.get_task.GetTaskViewModel;
import org.junit.Before;
import org.junit.Test;
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
import org.mockito.Mockito;
import view.AddProjectView;

import static org.junit.Assert.assertNotNull;

public class AddProjectUseCaseFactoryTest {

    private ViewManagerModel viewManagerModel;
    private AddedProjectViewModel addedProjectViewModel;
    private AddProjectViewModel addProjectViewModel;
    private TodoistDB userDataAccessObject;
    private GetProjectViewModel getProjectViewModel;
    private GetTaskViewModel getTaskViewModel;
    private DeleteProjectViewModel deleteProjectViewModel;

    @Before
    public void setUp() {
        viewManagerModel = Mockito.mock(ViewManagerModel.class);
        addedProjectViewModel = Mockito.mock(AddedProjectViewModel.class);
        addProjectViewModel = Mockito.mock(AddProjectViewModel.class);
        userDataAccessObject = Mockito.mock(TodoistDB.class);
        getProjectViewModel = Mockito.mock(GetProjectViewModel.class);
        getTaskViewModel = Mockito.mock(GetTaskViewModel.class);
        deleteProjectViewModel = Mockito.mock(DeleteProjectViewModel.class);
    }

    @Test
    public void testCreate() {
        AddProjectView addProjectView = AddProjectUseCaseFactory.create(
                viewManagerModel, addedProjectViewModel, addProjectViewModel,
                userDataAccessObject, getProjectViewModel, getTaskViewModel,
                deleteProjectViewModel);

        assertNotNull("AddProjectView should not be null", addProjectView);
    }
}

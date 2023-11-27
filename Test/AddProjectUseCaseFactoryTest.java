import api.TodoistDB;
import app.AddProjectUseCaseFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.get_task.GetTaskViewModel;
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
import view.AddProjectView;

import static org.junit.Assert.assertNotNull;

public class AddProjectUseCaseFactoryTest {

    @Test
    public void testCreate() {
        // Create the required dependencies
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        AddedProjectViewModel addedProjectViewModel = new AddedProjectViewModel();
        AddProjectViewModel addProjectViewModel = new AddProjectViewModel();
        TodoistDB userDataAccessObject = new TodoistDB();
        GetProjectViewModel getProjectViewModel = new GetProjectViewModel();
        GetTaskViewModel getTaskViewModel = new GetTaskViewModel();
        DeleteProjectViewModel deleteProjectViewModel = new DeleteProjectViewModel();

        // Call the create method of AddProjectUseCaseFactory
        AddProjectView addProjectView = AddProjectUseCaseFactory.create(viewManagerModel, addedProjectViewModel, addProjectViewModel, userDataAccessObject, getProjectViewModel, getTaskViewModel, deleteProjectViewModel);

        // Assert that the returned AddProjectView is not null
        assertNotNull(addProjectView);
    }
}
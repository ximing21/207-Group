package app;

import api.TodoistDB;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_project.AddProjectViewModel;
import interface_adapter.add_task.AddTaskViewModel;
import interface_adapter.close_task.CloseTaskViewModel;
import interface_adapter.get_task.GetTaskViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import view.GetTaskView;

import static org.junit.Assert.*;

public class GetTaskUseCaseFactoryTest {

    private GetTaskViewModel getTaskViewModel;
    private ViewManagerModel viewManagerModel;
    private AddProjectViewModel addProjectViewModel;
    private CloseTaskViewModel closeTaskViewModel;
    private TodoistDB userDataAccessObject;
    private AddTaskViewModel addTaskViewModel;

    @Before
    public void setUp() {
        getTaskViewModel = Mockito.mock(GetTaskViewModel.class);
        viewManagerModel = Mockito.mock(ViewManagerModel.class);
        addProjectViewModel = Mockito.mock(AddProjectViewModel.class);
        closeTaskViewModel = Mockito.mock(CloseTaskViewModel.class);
        userDataAccessObject = Mockito.mock(TodoistDB.class);
        addTaskViewModel = Mockito.mock(AddTaskViewModel.class);
    }

    @Test
    public void testCreate() {
        GetTaskView getTaskView = GetTaskUseCaseFactory.create(
                getTaskViewModel, viewManagerModel, addProjectViewModel,
                closeTaskViewModel, userDataAccessObject, addTaskViewModel);

        assertNotNull("GetTaskView should not be null", getTaskView);
    }
}

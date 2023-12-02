package interface_adapter.get_task;

import entity.Project;
import interface_adapter.ViewManagerModel;
import interface_adapter.get_all_projects.GetProjectPresenter;
import interface_adapter.get_all_projects.GetProjectState;
import interface_adapter.get_all_projects.GetProjectViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.get_all_projects.GetProjectOutputData;
import use_case.get_task.GetTaskInputBoundary;
import use_case.get_task.GetTaskInputData;

public class GetTaskControllerTest {

    private GetTaskInputBoundary mockInteractor;
    private GetTaskController controller;

    @Before
    public void setUp() {
        mockInteractor = Mockito.mock(GetTaskInputBoundary.class);
        controller = new GetTaskController(mockInteractor);
    }

    @Test
    public void testExecute() {
        String taskName = "Test Task";

        controller.execute(taskName);

        Mockito.verify(mockInteractor).execute(Mockito.any(GetTaskInputData.class));
    }
}

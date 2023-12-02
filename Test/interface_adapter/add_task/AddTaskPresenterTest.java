package interface_adapter.add_task;

import interface_adapter.ViewManagerModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.add_task.AddTaskOutputData;

public class AddTaskPresenterTest {

    private AddTaskViewModel mockViewModel;
    private ViewManagerModel mockViewManagerModel;
    private AddTaskPresenter presenter;
    private AddTaskState mockState;

    @Before
    public void setUp() {
        mockViewModel = Mockito.mock(AddTaskViewModel.class);
        mockViewManagerModel = Mockito.mock(ViewManagerModel.class); // Mocking the ViewManagerModel
        mockState = Mockito.mock(AddTaskState.class);

        Mockito.when(mockViewModel.getState()).thenReturn(mockState);

        // Now providing both required arguments to the constructor
        presenter = new AddTaskPresenter(mockViewModel, mockViewManagerModel);
    }

    @Test
    public void testPrepareSuccessView() {
        AddTaskOutputData data = new AddTaskOutputData("Task", "taskId", "2023-12-15");
        presenter.prepareSuccessView(data);

        Mockito.verify(mockState).setTask_name("Task");
        Mockito.verify(mockState).setTaskId("taskId");
        Mockito.verify(mockState).setTaskDeadline("2023-12-15");
        Mockito.verify(mockViewModel).setState(mockState);
    }

    @Test
    public void testPrepareFailViewWithError() {
        presenter.prepareFailView("Sample Error");
        // You can't assert behavior here since the method is empty
        // but this call ensures line coverage.
    }

    @Test
    public void testPrepareFailViewWithoutError() {
        presenter.prepareFailView();
        // Similar to above, this test is for line coverage.
    }
}



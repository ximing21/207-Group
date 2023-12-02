package interface_adapter.get_task;

import entity.Task;
import interface_adapter.ViewManagerModel;
import kotlin.Pair;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.get_task.GetTaskInputBoundary;
import use_case.get_task.GetTaskInputData;
import use_case.get_task.GetTaskOutputData;

import java.util.ArrayList;

public class GetTaskPresenterTest {

    private GetTaskViewModel mockViewModel;
    private ViewManagerModel mockViewManagerModel;
    private GetTaskPresenter presenter;
    private GetTaskState mockState;

    @Before
    public void setUp() {
        mockViewModel = Mockito.mock(GetTaskViewModel.class);
        mockViewManagerModel = Mockito.mock(ViewManagerModel.class);
        mockState = Mockito.mock(GetTaskState.class);

        Mockito.when(mockViewModel.getState()).thenReturn(mockState);
        Mockito.when(mockViewModel.getViewName()).thenReturn("get task"); // Return a valid view name

        presenter = new GetTaskPresenter(mockViewModel, mockViewManagerModel);
    }

    @Test
    public void testPrepareSuccessView() {
        String projectName = "Test Project";
        String message = "Test Message";
        ArrayList<Task> tasks = new ArrayList<>();
        Pair<String, ArrayList<Task>> result = new Pair<>(projectName, tasks);

        GetTaskOutputData outputData = new GetTaskOutputData(result, message);

        presenter.prepareSuccessView(outputData);

        Mockito.verify(mockState).setTasks(tasks);
        Mockito.verify(mockState).setMessage(message);
        Mockito.verify(mockViewModel).setState(mockState);
        Mockito.verify(mockViewModel).setTitleLabel(projectName);
        Mockito.verify(mockViewModel).firePropertyChanged();
        Mockito.verify(mockViewManagerModel).setActiveView("get task"); // Verify with the correct view name
        Mockito.verify(mockViewManagerModel).firePropertyChanged();
    }
}


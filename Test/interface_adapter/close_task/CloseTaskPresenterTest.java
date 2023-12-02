package interface_adapter.close_task;

import interface_adapter.ViewManagerModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.close_task.CloseTaskInputBoundary;
import use_case.close_task.CloseTaskInputData;
import use_case.close_task.CloseTaskOutputData;

public class CloseTaskPresenterTest {

    private CloseTaskViewModel mockViewModel;
    private ViewManagerModel mockViewManagerModel;
    private CloseTaskPresenter presenter;
    private CloseTaskState mockState;  // Mocked CloseTaskState

    @Before
    public void setUp() {
        mockViewModel = Mockito.mock(CloseTaskViewModel.class);
        mockViewManagerModel = Mockito.mock(ViewManagerModel.class);
        mockState = Mockito.mock(CloseTaskState.class);  // Create a mock CloseTaskState

        Mockito.when(mockViewModel.getState()).thenReturn(mockState);  // Ensure getState() returns the mock state

        presenter = new CloseTaskPresenter(mockViewModel, mockViewManagerModel);
    }

    @Test
    public void testPrepareSuccessView() {
        CloseTaskOutputData outputData = new CloseTaskOutputData(true);

        presenter.prepareSuccessView(outputData);

        // Verify interactions with the mockState
        Mockito.verify(mockViewModel).setState(mockState);
        Mockito.verify(mockViewModel).firePropertyChanged();
    }
}

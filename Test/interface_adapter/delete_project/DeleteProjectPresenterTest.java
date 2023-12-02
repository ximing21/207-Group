package interface_adapter.delete_project;

import interface_adapter.ViewManagerModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.delete_project.DeleteProjectInputBoundary;
import use_case.delete_project.DeleteProjectInputData;
import use_case.delete_project.DeleteProjectOutputData;

public class DeleteProjectPresenterTest {

    private DeleteProjectViewModel mockViewModel;
    private ViewManagerModel mockViewManagerModel;
    private DeleteProjectPresenter presenter;
    private DeleteProjectState mockState;  // Added mock for DeleteProjectState

    @Before
    public void setUp() {
        mockViewModel = Mockito.mock(DeleteProjectViewModel.class);
        mockViewManagerModel = Mockito.mock(ViewManagerModel.class);
        mockState = Mockito.mock(DeleteProjectState.class);  // Initialize the mockState

        // Configure the mock to return the mockState when getState() is called
        Mockito.when(mockViewModel.getState()).thenReturn(mockState);

        presenter = new DeleteProjectPresenter(mockViewModel, mockViewManagerModel);
    }

    @Test
    public void testPrepareSuccessView() {
        DeleteProjectOutputData outputData = new DeleteProjectOutputData("Test Project");

        presenter.prepareSuccessView(outputData);

        // Verify that setProject_name on mockState is called with the expected value
        Mockito.verify(mockState).setProject_name("Test Project");
        Mockito.verify(mockViewModel).setState(mockState);
        Mockito.verify(mockViewModel).firePropertyChanged();
    }
}

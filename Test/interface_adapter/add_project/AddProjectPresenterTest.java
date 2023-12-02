package interface_adapter.add_project;

import interface_adapter.ViewManagerModel;
import interface_adapter.added_project.AddedProjectState;
import interface_adapter.added_project.AddedProjectViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.add_project.AddProjectInputBoundary;
import use_case.add_project.AddProjectInputData;
import use_case.add_project.AddProjectOutputData;

public class AddProjectPresenterTest {

    private AddProjectViewModel mockAddProjectViewModel;
    private AddedProjectViewModel mockAddedProjectViewModel;
    private ViewManagerModel mockViewManagerModel;
    private AddProjectPresenter presenter;
    private AddProjectState mockAddProjectState;

    @Before
    public void setUp() {
        mockAddProjectViewModel = Mockito.mock(AddProjectViewModel.class);
        mockAddedProjectViewModel = Mockito.mock(AddedProjectViewModel.class);
        mockViewManagerModel = Mockito.mock(ViewManagerModel.class);
        mockAddProjectState = Mockito.mock(AddProjectState.class);

        // Setup the mock to return a non-null state
        Mockito.when(mockAddProjectViewModel.getState()).thenReturn(mockAddProjectState);

        presenter = new AddProjectPresenter(mockAddProjectViewModel, mockAddedProjectViewModel, mockViewManagerModel);
    }

    @Test
    public void testPrepareFailView() {
        String error = "Error Message";

        presenter.prepareFailView(error);

        // Verify interactions with the state object
        Mockito.verify(mockAddProjectState).setProject_nameError(error);
        Mockito.verify(mockAddProjectViewModel).firePropertyChanged();
    }
}

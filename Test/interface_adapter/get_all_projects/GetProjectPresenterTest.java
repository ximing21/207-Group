package interface_adapter.get_all_projects;

import entity.Project;
import interface_adapter.ViewManagerModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.get_all_projects.GetProjectInputBoundary;
import use_case.get_all_projects.GetProjectOutputData;

public class GetProjectPresenterTest {

    private GetProjectViewModel mockViewModel;
    private ViewManagerModel mockViewManagerModel;
    private GetProjectPresenter presenter;
    private GetProjectState mockState;  // Added mock for GetProjectState

    @Before
    public void setUp() {
        mockViewModel = Mockito.mock(GetProjectViewModel.class);
        mockViewManagerModel = Mockito.mock(ViewManagerModel.class);
        mockState = Mockito.mock(GetProjectState.class);  // Initialize the mockState

        // Configure the mock to return the mockState when getState() is called
        Mockito.when(mockViewModel.getState()).thenReturn(mockState);

        presenter = new GetProjectPresenter(mockViewModel, mockViewManagerModel);
    }

    @Test
    public void testPrepareSuccessView() {
        Project[] mockProjects = new Project[0];
        GetProjectOutputData outputData = new GetProjectOutputData(mockProjects);

        presenter.prepareSuccessView(outputData);

        // Verify that setProjects on mockState is called with the expected projects
        Mockito.verify(mockState).setProjects(mockProjects);
        Mockito.verify(mockViewModel).setState(mockState);
        Mockito.verify(mockViewModel).firePropertyChanged();
    }
}

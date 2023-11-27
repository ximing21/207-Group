import entity.Project;
import interface_adapter.ViewManagerModel;
import interface_adapter.get_all_projects.GetProjectPresenter;
import interface_adapter.get_all_projects.GetProjectState;
import interface_adapter.get_all_projects.GetProjectViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.get_all_projects.GetProjectOutputData;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GetProjectPresenterTest {

    private GetProjectViewModel getProjectViewModel;
    private ViewManagerModel viewManagerModel;
    private GetProjectPresenter getProjectPresenter;

    @Before
    public void setUp() {
        getProjectViewModel = mock(GetProjectViewModel.class);
        viewManagerModel = mock(ViewManagerModel.class);
        getProjectPresenter = new GetProjectPresenter(getProjectViewModel, viewManagerModel);
    }

    @Test
    public void testPrepareSuccessView() {
        // Arrange
        GetProjectOutputData response = mock(GetProjectOutputData.class);
        GetProjectState getProjectState = mock(GetProjectState.class);
        when(getProjectViewModel.getState()).thenReturn(getProjectState);

        // Act
        getProjectPresenter.prepareSuccessView(response);

        // Assert
        verify(getProjectViewModel).setState(getProjectState);
        verify(getProjectViewModel).firePropertyChanged();
    }

    @Test
    public void testPrepareFailView() {
        // Arrange
        String error = "Error message";

        // Act
        getProjectPresenter.prepareFailView(error);

        // Assert
        // No assertions needed as the method does not have any logic
    }
}
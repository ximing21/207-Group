package use_case;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_project.AddProjectViewModel;
import interface_adapter.switch_view.SwitchViewPresenter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.switch_view.SwitchViewInteractor;
import use_case.switch_view.SwitchViewOutputBoundary;

import static org.mockito.Mockito.verify;

public class SwitchViewTest {

    private AddProjectViewModel addProjectViewModel;
    private ViewManagerModel viewManagerModel;
    private SwitchViewPresenter switchViewPresenter;
    private SwitchViewOutputBoundary presenter;
    private SwitchViewInteractor switchViewInteractor;

    @Before
    public void setUp() {

        addProjectViewModel = Mockito.mock(AddProjectViewModel.class);
        viewManagerModel = Mockito.mock(ViewManagerModel.class);
        presenter = Mockito.mock(SwitchViewOutputBoundary.class);


        Mockito.when(addProjectViewModel.getViewName()).thenReturn("AddProjectView");


        switchViewPresenter = new SwitchViewPresenter(addProjectViewModel, viewManagerModel);
        switchViewInteractor = new SwitchViewInteractor(presenter);
    }

    @Test
    public void testSwitchViewPresenterPrepareSuccessView() {

        switchViewPresenter.prepareSuccessView();

        verify(viewManagerModel).setActiveView("AddProjectView");
        verify(viewManagerModel).firePropertyChanged();
    }

    @Test
    public void testSwitchViewInteractorExecute() {

        switchViewInteractor.execute();

        verify(presenter).prepareSuccessView();
    }
}

package use_case;

import api.TodoistDB;
import entity.Project;
import interface_adapter.ViewManagerModel;
import interface_adapter.get_all_projects.GetProjectPresenter;
import interface_adapter.get_all_projects.GetProjectState;
import interface_adapter.get_all_projects.GetProjectViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.get_all_projects.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class GetProjectPresenterTest {

    private GetProjectDataAccessInterface dataAccessObject;
    private GetProjectOutputBoundary successPresenter;

    @Before
    public void setUp() {
        dataAccessObject = new TodoistDB();
        successPresenter = new GetProjectOutputBoundary() {
            @Override
            public void prepareSuccessView(GetProjectOutputData projects) {
                assertEquals("Inbox", projects.getPojects()[0].getName());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };
    }

    @Test
    public void successTest() {
        GetProjectInputBoundary interactor = new GetProjectInteractor(successPresenter, dataAccessObject);
        interactor.execute();
    }
}

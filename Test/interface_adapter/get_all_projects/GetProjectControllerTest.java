package interface_adapter.get_all_projects;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.get_all_projects.GetProjectInputBoundary;

public class GetProjectControllerTest {

    private GetProjectInputBoundary mockInteractor;
    private GetProjectController controller;

    @Before
    public void setUp() {
        mockInteractor = Mockito.mock(GetProjectInputBoundary.class);
        controller = new GetProjectController(mockInteractor);
    }

    @Test
    public void testExecute() {
        controller.execute();
        Mockito.verify(mockInteractor).execute();
    }
}

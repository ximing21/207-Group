package interface_adapter.add_project;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.add_project.AddProjectInputBoundary;
import use_case.add_project.AddProjectInputData;

public class AddProjectControllerTest {

    private AddProjectInputBoundary mockInteractor;
    private AddProjectController controller;

    @Before
    public void setUp() {
        mockInteractor = Mockito.mock(AddProjectInputBoundary.class);
        controller = new AddProjectController(mockInteractor);
    }

    @Test
    public void testExecute() {
        String projectName = "Test Project";

        controller.execute(projectName);

        Mockito.verify(mockInteractor).execute(Mockito.any(AddProjectInputData.class));
    }
}

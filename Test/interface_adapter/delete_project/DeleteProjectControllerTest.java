package interface_adapter.delete_project;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.delete_project.DeleteProjectInputBoundary;
import use_case.delete_project.DeleteProjectInputData;

public class DeleteProjectControllerTest {

    private DeleteProjectInputBoundary mockInteractor;
    private DeleteProjectController controller;

    @Before
    public void setUp() {
        mockInteractor = Mockito.mock(DeleteProjectInputBoundary.class);
        controller = new DeleteProjectController(mockInteractor);
    }

    @Test
    public void testExecute() {
        String projectName = "Test Project";

        controller.execute(projectName);

        Mockito.verify(mockInteractor).execute(Mockito.any(DeleteProjectInputData.class));
    }
}

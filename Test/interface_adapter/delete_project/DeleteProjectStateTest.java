package interface_adapter.delete_project;

import interface_adapter.ViewManagerModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.delete_project.DeleteProjectOutputData;

import static junit.framework.TestCase.assertEquals;

public class DeleteProjectStateTest {

    @Test
    public void testProjectNameGetterSetter() {
        DeleteProjectState state = new DeleteProjectState();
        String expectedProjectName = "Test Project";

        state.setProject_name(expectedProjectName);
        String actualProjectName = state.getProject_name();

        assertEquals("The project name should match the set value", expectedProjectName, actualProjectName);
    }
}

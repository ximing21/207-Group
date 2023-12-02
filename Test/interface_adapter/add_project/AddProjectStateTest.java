package interface_adapter.add_project;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class AddProjectStateTest {

    @Test
    public void testGettersAndSetters() {
        AddProjectState state = new AddProjectState();
        String projectName = "Test Project";
        String projectError = "Error Message";

        state.setProject_name(projectName);
        state.setProject_nameError(projectError);

        assertEquals(projectName, state.getProject_name());
        assertEquals(projectError, state.getProject_nameError());
    }
}

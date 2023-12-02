package interface_adapter.added_project;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class AddedProjectStateTest {

    @Test
    public void testGetAndSetProjectname() {
        AddedProjectState state = new AddedProjectState();
        String expectedProjectName = "Test Project";

        state.setProjectname(expectedProjectName);
        String actualProjectName = state.getProjectname();

        assertEquals("The project name should match the set value", expectedProjectName, actualProjectName);
    }
}

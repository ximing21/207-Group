package interface_adapter.get_all_projects;

import entity.Project;
import interface_adapter.ViewManagerModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.get_all_projects.GetProjectOutputData;

import static org.junit.Assert.assertArrayEquals;

public class GetProjectStateTest {

    @Test
    public void testGetAndSetProjects() {
        GetProjectState state = new GetProjectState();
        Project[] expectedProjects = new Project[]{/* Mock projects */};

        state.setProjects(expectedProjects);
        Project[] actualProjects = state.getProjects();

        assertArrayEquals("The projects array should match the set value", expectedProjects, actualProjects);
    }
}

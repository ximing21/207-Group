import entity.Project;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProjectTest {

    @Test
    public void testGetName() {
        Project project = Project.builder()
                .ProjectId("123")
                .ProjectName("Test Project")
                .TaskCount(5)
                .build();

        assertEquals("Test Project", project.getName());
    }

    @Test
    public void testGetTaskCount() {
        Project project = Project.builder()
                .ProjectId("123")
                .ProjectName("Test Project")
                .TaskCount(5)
                .build();

        assertEquals(5, project.getTaskCount());
    }
}
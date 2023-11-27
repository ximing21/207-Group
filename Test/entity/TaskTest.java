package entity;

import entity.Task;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskTest {

    @Test
    public void testGetTaskId() {
        Task task = Task.builder()
                .TaskId("1")
                .TaskName("Task 1")
                .ProjectId("P1")
                .Deadline("2022-12-31")
                .build();

        assertEquals("1", task.getTaskId());
    }

    @Test
    public void testGetTaskName() {
        Task task = Task.builder()
                .TaskId("1")
                .TaskName("Task 1")
                .ProjectId("P1")
                .Deadline("2022-12-31")
                .build();

        assertEquals("Task 1", task.getTaskName());
    }

    @Test
    public void testGetProjectId() {
        Task task = Task.builder()
                .TaskId("1")
                .TaskName("Task 1")
                .ProjectId("P1")
                .Deadline("2022-12-31")
                .build();

        assertEquals("P1", task.getProjectId());
    }

    @Test
    public void testGetDeadline() {
        Task task = Task.builder()
                .TaskId("1")
                .TaskName("Task 1")
                .ProjectId("P1")
                .Deadline("2022-12-31")
                .build();

        assertEquals("2022-12-31", task.getDeadline());
    }

    @Test
    public void testBuilder() {
        Task.TaskBuilder builder = Task.builder();

        assertEquals(Task.TaskBuilder.class, builder.getClass());
    }

    @Test
    public void testBuild() {
        Task task = Task.builder()
                .TaskId("1")
                .TaskName("Task 1")
                .ProjectId("P1")
                .Deadline("2022-12-31")
                .build();

        assertEquals("1", task.getTaskId());
        assertEquals("Task 1", task.getTaskName());
        assertEquals("P1", task.getProjectId());
        assertEquals("2022-12-31", task.getDeadline());
    }
}
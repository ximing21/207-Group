package use_case;

import api.TodoistDB;
import entity.Project;
import interface_adapter.ViewManagerModel;
import interface_adapter.get_all_projects.GetProjectPresenter;
import interface_adapter.get_all_projects.GetProjectState;
import interface_adapter.get_all_projects.GetProjectViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.add_project.AddProjectDataAccessInterface;
import use_case.get_all_projects.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetProjectTest {

    private AddProjectDataAccessInterface addProjectDataAccessObject;
    private GetProjectDataAccessInterface getProjectDataAccessObject;
    private GetProjectOutputBoundary successPresenter;
    private List<Project> projectList; // 用于存储模拟的项目列表

    @Before
    public void setUp() {
        addProjectDataAccessObject = Mockito.mock(AddProjectDataAccessInterface.class);
        getProjectDataAccessObject = Mockito.mock(GetProjectDataAccessInterface.class);
        projectList = new ArrayList<>(); // 初始化空项目列表

        // 模拟添加项目的行为
        Mockito.doAnswer(invocation -> {
            String projectName = invocation.getArgument(0);
            projectList.add(new Project("dummy_id", projectName, 0)); // 添加新项目
            return null;
        }).when(addProjectDataAccessObject).createProject(Mockito.anyString());

        // 模拟获取所有项目的行为
        Mockito.when(getProjectDataAccessObject.getProject()).thenAnswer(invocation ->
                projectList.toArray(new Project[0]) // 返回当前项目列表
        );

        successPresenter = new GetProjectOutputBoundary() {
            @Override
            public void prepareSuccessView(GetProjectOutputData projects) {
                assertNotNull("Projects should not be null", projects);
                assertTrue("Project list should contain 'New Project'",
                        Arrays.stream(projects.getPojects())
                                .anyMatch(p -> "New Project".equals(p.getName())));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected: " + error);
            }
        };
    }

    @Test
    public void successTest() {
        // Simulate adding a new project
        addProjectDataAccessObject.createProject("New Project");

        // Test getting all projects
        GetProjectInputBoundary interactor = new GetProjectInteractor(successPresenter, getProjectDataAccessObject);
        interactor.execute();

        // Optionally verify interactions
        Mockito.verify(getProjectDataAccessObject).getProject();
    }
}

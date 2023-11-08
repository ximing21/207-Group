package api;
import entity.Project;
import entity.Task;
import use_case.add_project.AddProjectDataAccessInterface;

public interface TodolistDB {
    void createProject(String name);
    Project updateProject();
    Project[] getProject();
    void createTask();
    Task getActiveTask();
    void deleteTask();

}

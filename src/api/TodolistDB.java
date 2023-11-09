package api;
import entity.Project;
import entity.Task;

public interface TodolistDB {
    void createProject(String name);
    Project updateProject();
    Project[] getProject();
    void createTask();
    Task getActiveTask();
    void deleteTask();

}

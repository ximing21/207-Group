package api;
import entity.Project;
import entity.Task;
import kotlin.Pair;
import use_case.add_project.AddProjectDataAccessInterface;
import use_case.add_task.AddTaskDataAccessInterface;
import use_case.close_task.CloseTaskDataAccessInterface;
import use_case.delete_project.DeleteProjectDataAccessInterface;
import use_case.get_all_projects.GetProjectDataAccessInterface;
import use_case.get_task.GetTaskDataAccessInterface;

import java.util.ArrayList;

public interface TodolistDB {
    public void createProject(String name);
    public Integer deleteProject(String projectName);
    public Project[] getProject();
    public void closeTask(String taskId);
    public Pair<String, ArrayList<Task>> getTasks(String name);
    public String addTask(String taskName, String projectName, String deadline);
    public boolean existsByName(String name);

}

package use_case.get_task;

import entity.Task;
import kotlin.Pair;

import java.util.ArrayList;


public interface GetTaskDataAccessInterface {
    boolean existsByName(String name);
    Pair<String, ArrayList<Task>> getTasks(String name);
    public String getMessage();
}

package use_case.add_task;


public interface AddTaskDataAccessInterface {
    String addTask(String taskName, String projectName, String deadline);

}

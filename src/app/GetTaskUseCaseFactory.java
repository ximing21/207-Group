package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.get_task.GetTaskController;
import interface_adapter.get_task.GetTaskViewModel;
import use_case.get_task.GetTaskDataAccessInterface;
import view.GetTaskView;

import javax.swing.*;
import java.io.IOException;

public class GetTaskUseCaseFactory {

    private GetTaskUseCaseFactory() {
    }

    public static GetTaskView create(
            GetTaskViewModel getTaskViewModel) {

        return new GetTaskView(getTaskViewModel);
    }

}
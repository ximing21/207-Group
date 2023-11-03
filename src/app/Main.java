package app;

import api.TodoistDB;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_project.AddProjectViewModel;
import interface_adapter.add_task.AddTaskViewModel;
import view.AddProjectView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Deadline Defenders");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  //TODO:
        application.setSize(400, 300);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        AddProjectViewModel addProjectViewModel = new AddProjectViewModel();
        AddTaskViewModel addTaskViewModel = new AddTaskViewModel();

//TODO:
        TodoistDB userDataAccessObject;
        userDataAccessObject = new TodoistDB();

        AddProjectView addProjectView =
                AddProjectUseCaseFactory.create(viewManagerModel, addTaskViewModel, addProjectViewModel,
                        userDataAccessObject);
        views.add(addProjectView, addProjectView.viewName);

        viewManagerModel.setActiveView(addProjectView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }

}

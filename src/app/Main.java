package app;

import api.TodoistDB;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_project.AddProjectViewModel;
import interface_adapter.added_project.AddedProjectViewModel;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import view.AddProjectView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Deadline Defenders");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
        AddedProjectViewModel addedProjectViewModel = new AddedProjectViewModel();

        TodoistDB userDataAccessObject;
        userDataAccessObject = new TodoistDB();

        AddProjectView addProjectView =
                AddProjectUseCaseFactory.create(viewManagerModel, addedProjectViewModel, addProjectViewModel,
                        userDataAccessObject);
        views.add(addProjectView, addProjectView.viewName);

        viewManagerModel.setActiveView(addProjectView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
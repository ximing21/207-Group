package interface_adapter.add_project;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddProjectViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Project Lists";
    public static final String ADD_PROJECT_BUTTON_LABEL = "Add Project";
    public static final String GET_PROJECT_BUTTON_LABEL = "Get All Projects";
    public static final String PROJECT_NAME_LABEL = "Choose project name:";
    private AddProjectState state = new AddProjectState();


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public AddProjectViewModel() {
        super("add project");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
     public AddProjectState getState() {return state;}
     public void setState(AddProjectState state) {
        this.state = state;
    }
}

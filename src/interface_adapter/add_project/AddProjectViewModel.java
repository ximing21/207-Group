package interface_adapter.add_project;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddProjectViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Add Project View";
    public static final String ADD_PROJECT_BUTTON_LABEL = "Add Project";
    private AddProjectState state = new AddProjectState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public AddProjectViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
     public AddProjectState getState() {return state;}
}

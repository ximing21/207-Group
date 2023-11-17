package interface_adapter.delete_project;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DeleteProjectViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Delete Project";
    public static final String YES_CANCEL_PROJECT_BUTTON_LABEL = "Yes";
    public static final String NO_CANCEL_PROJECT_BUTTON_LABEL = "No";
    public static final String PROJECT_NAME_LABEL = "Choose project name:";
    private DeleteProjectState state = new DeleteProjectState();


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public DeleteProjectViewModel() {
        super("delete project");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public DeleteProjectState getState() {return state;}
    public void setState(DeleteProjectState state) {
        this.state = state;
    }

}

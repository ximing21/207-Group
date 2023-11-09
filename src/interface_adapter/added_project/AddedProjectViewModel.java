package interface_adapter.added_project;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddedProjectViewModel extends ViewModel {
    private AddedProjectState state = new AddedProjectState();

    public void setState(AddedProjectState state) {
        this.state = state;
    }

    public AddedProjectState getState() {
        return state;
    }


    public AddedProjectViewModel() {
        super("project added");
    }



    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Login Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

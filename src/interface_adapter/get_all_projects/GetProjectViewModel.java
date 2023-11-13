package interface_adapter.get_all_projects;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GetProjectViewModel extends ViewModel {
    private GetProjectState state = new GetProjectState();


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public GetProjectViewModel() {
        super("Get project");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
     public GetProjectState getState() {return state;}
     public void setState(GetProjectState state) {
        this.state = state;
    }
}

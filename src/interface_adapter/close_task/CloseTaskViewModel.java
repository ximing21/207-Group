package interface_adapter.close_task;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CloseTaskViewModel extends ViewModel {
    private CloseTaskState state = new CloseTaskState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public CloseTaskViewModel() {super("close task");}


    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);}


    public CloseTaskState getState() {
        return state;
    }

    public void setState(CloseTaskState state) {
        this.state = state;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}
}

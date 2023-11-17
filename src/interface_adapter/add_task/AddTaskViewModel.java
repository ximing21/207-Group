package interface_adapter.add_task;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddTaskViewModel extends ViewModel {

    private AddTaskState state = new AddTaskState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public AddTaskViewModel() {
        super("add task");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public AddTaskState getState() {return state;}
    public void setState(AddTaskState state) {
        this.state = state;
    }

}


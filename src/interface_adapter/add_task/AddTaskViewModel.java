package interface_adapter.add_task;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddTaskViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Add Task View";
    public static final String PROJECT_NAME_LABEL = "Project:";
    public static final String ADD_TASK_BUTTON_LABEL = "Add Task";
    public static final String GET_TASK_BUTTON_LABEL = "Get All Tasks";

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


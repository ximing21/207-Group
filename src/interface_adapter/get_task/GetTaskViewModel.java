package interface_adapter.get_task;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GetTaskViewModel extends ViewModel {
    public static String TITLE_LABEL;
    public static final String ADD_TASK_BUTTON_LABEL = "Add Task";
    public static final String TASK_NAME_LABEL = "Choose task name:";
    public static final String DEADLINE_LABEL = "Due date(YYYY-MM-DD):";
    private GetTaskState state = new GetTaskState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public GetTaskViewModel() {super("get task");}


    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);}

    public void setTITLE_LABEL(String TITLE_LABEL) {this.TITLE_LABEL = TITLE_LABEL;}

    public GetTaskState getState() {
        return state;
    }

    public void setState(GetTaskState state) {
        this.state = state;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {support.addPropertyChangeListener(listener);}
}
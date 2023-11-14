package view;



import entity.Project;
import entity.Task;
import interface_adapter.get_all_projects.GetProjectState;
import interface_adapter.get_task.GetTaskState;
import interface_adapter.get_task.GetTaskViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class GetTaskView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "get task";
    private final GetTaskViewModel getTaskViewModel;
    private JList<String> projectList;
    private DefaultListModel<String> listModel;
    private final JTextField taskNameInputField = new JTextField(15);
    private final JTextField deadlineInputField = new JTextField(10);

    private final JButton addTask;



    public GetTaskView(GetTaskViewModel getTaskViewModel) {
        this.getTaskViewModel = getTaskViewModel;

        getTaskViewModel.addPropertyChangeListener(this);

        this.listModel = new DefaultListModel<>();
        this.projectList = new JList<>(listModel);

        JLabel title = new JLabel(GetTaskViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setForeground(Color.darkGray);

        LabelTextPanel taskNameInfo = new LabelTextPanel(
                new JLabel(GetTaskViewModel.TASK_NAME_LABEL), taskNameInputField);

        LabelTextPanel deadlineInfo = new LabelTextPanel(
                new JLabel(GetTaskViewModel.DEADLINE_LABEL), deadlineInputField);

        Font font = new Font("SansSerif", Font.PLAIN,15);
        addTask = new JButton(GetTaskViewModel.ADD_TASK_BUTTON_LABEL);
        addTask.setFont(font);

        JPanel inputPanel = new JPanel();
        inputPanel.add(taskNameInfo);
        inputPanel.add(deadlineInfo);
        inputPanel.add(addTask, BorderLayout.AFTER_LINE_ENDS);

        JScrollPane scrollPane = new JScrollPane(projectList);
        scrollPane.setPreferredSize(new Dimension(80,100));

        this.setPreferredSize(new Dimension(850, 300));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(inputPanel);
        this.add(scrollPane, BorderLayout.CENTER);
        }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object state = evt.getNewValue();
        if (state instanceof GetTaskState) {
            GetTaskState getTaskState = (GetTaskState) state;
            java.util.List<Task> tasks = getTaskState.getTasks();
            listModel.clear();
            for (Task task : tasks) {
                String taskName = task.getTaskName();
                listModel.addElement(taskName);
            }
        }
    }
}


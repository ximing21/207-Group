package view;


import entity.Task;
import interface_adapter.close_task.CloseTaskController;
import interface_adapter.get_task.GetTaskState;
import interface_adapter.get_task.GetTaskViewModel;
import interface_adapter.switch_view.SwitchViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GetTaskView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "get task";
    private final GetTaskViewModel getTaskViewModel;
    private final SwitchViewController switchViewController;
    private final CloseTaskController closeTaskController;
    private final JTextField taskNameInputField = new JTextField(15);
    private final JTextField deadlineInputField = new JTextField(10);

    private final JButton addTask;
    private final JButton backToProjects;
    private JTextArea tasksArea;




    public GetTaskView(GetTaskViewModel getTaskViewModel, SwitchViewController switchViewController, CloseTaskController closeTaskController) {
        this.getTaskViewModel = getTaskViewModel;
        this.switchViewController = switchViewController;
        this.closeTaskController = closeTaskController;

        getTaskViewModel.addPropertyChangeListener(this);

        this.tasksArea = new JTextArea(10, 30);
        tasksArea.setEditable(false); // 禁止在 tasksArea 中输入文本
        tasksArea.setLayout(new BoxLayout(tasksArea, BoxLayout.Y_AXIS));



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
        JPanel buttons = new JPanel();
        backToProjects = new JButton((GetTaskViewModel.PROJECTS_BUTTON_LABEL));
        backToProjects.setFont(font);
        buttons.add(backToProjects);


        backToProjects.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(backToProjects)) {
                            switchViewController.execute();
                        }
                    }
                }
        );


        JPanel inputPanel = new JPanel();
        inputPanel.add(taskNameInfo);
        inputPanel.add(deadlineInfo);
        inputPanel.add(addTask, BorderLayout.AFTER_LINE_ENDS);

        JScrollPane scrollPane = new JScrollPane(tasksArea);
        scrollPane.setPreferredSize(new Dimension(80,150));

        this.setPreferredSize(new Dimension(850, 300));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(inputPanel);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttons);
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
            tasksArea.removeAll();
            for (Task task : tasks) {
                JCheckBox checkBox = new JCheckBox(task.getTaskName());
                checkBox.addActionListener(e -> {
                    if (checkBox.isSelected()) {
                        try {
                            closeTaskController.execute(task.getTaskId());
                            checkBox.setEnabled(false);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                tasksArea.add(checkBox);
            }
        }
        tasksArea.revalidate();
        tasksArea.repaint();
    }
}


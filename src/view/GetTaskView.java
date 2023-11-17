package view;


import entity.Task;
import interface_adapter.add_project.AddProjectState;
import interface_adapter.add_task.AddTaskController;
import interface_adapter.add_task.AddTaskState;
import interface_adapter.add_task.AddTaskViewModel;
import interface_adapter.added_project.AddedProjectState;
import interface_adapter.close_task.CloseTaskController;
import interface_adapter.get_all_projects.GetProjectState;
import interface_adapter.get_task.GetTaskState;
import interface_adapter.get_task.GetTaskViewModel;
import interface_adapter.switch_view.SwitchViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Comparator;

public class GetTaskView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "get task";
    private final JLabel projectNameTitle;
    private final GetTaskViewModel getTaskViewModel;
    private final AddTaskViewModel addTaskViewModel;
    private final SwitchViewController switchViewController;
    private final CloseTaskController closeTaskController;
    private final AddTaskController addTaskController;
    private final JTextField taskNameInputField = new JTextField(15);
    private final JTextField deadlineInputField = new JTextField(10);

    private final JButton addTask;
    private final JButton backToProjects;
    private JTextArea tasksArea;




    public GetTaskView(GetTaskViewModel getTaskViewModel,
                       SwitchViewController switchViewController,
                       CloseTaskController closeTaskController,
                       AddTaskController addTaskController,
                       AddTaskViewModel addTaskViewModel) {
        this.getTaskViewModel = getTaskViewModel;
        this.switchViewController = switchViewController;
        this.closeTaskController = closeTaskController;
        this.addTaskController = addTaskController;
        this.addTaskViewModel = addTaskViewModel;

        getTaskViewModel.addPropertyChangeListener(this);

        this.tasksArea = new JTextArea(10, 30);
        tasksArea.setEditable(false); // 禁止在 tasksArea 中输入文本
        tasksArea.setLayout(new BoxLayout(tasksArea, BoxLayout.Y_AXIS));



        this.projectNameTitle = new JLabel(getTaskViewModel.getTitleLabel());
        projectNameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        projectNameTitle.setFont(new Font("Serif", Font.BOLD, 30));
        projectNameTitle.setForeground(Color.darkGray);

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


        addTask.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(addTask)) {

                            String taskName = taskNameInputField.getText().trim();
                            String deadline = deadlineInputField.getText().trim();

                            // Regular expression for the date format YYYY-MM-DD
                            String dateFormatRegex = "^\\d{4}-\\d{2}-\\d{2}$";

                            if (!taskName.isEmpty() && (deadline.isEmpty() || deadline.matches(dateFormatRegex))) {
                                try {
                                    addTaskController.execute(taskName, deadline, getTaskViewModel.getTitleLabel());
                                    taskNameInputField.setText(""); // Clear the input field
                                    deadlineInputField.setText(""); // Clear the input field

                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else if (!deadline.isEmpty() && !deadline.matches(dateFormatRegex)) {
                            // Show error message if the deadline format is incorrect
                            JOptionPane.showMessageDialog(null, "Invalid deadline format. Please use YYYY-MM-DD.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        }
                        }

                    }
                }
        );

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
        this.add(projectNameTitle);
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

        if ("titleLabel".equals(evt.getPropertyName())) {
            projectNameTitle.setText((String) evt.getNewValue());
            projectNameTitle.revalidate();
            projectNameTitle.repaint();
        }

        if (state instanceof GetTaskState) {
            GetTaskState getTaskState = (GetTaskState) state;

            java.util.List<Task> tasks = new ArrayList<>(getTaskState.getTasks());
            tasks.sort(Comparator.comparing(Task::IsCompleted));
            tasksArea.removeAll();
            for (Task task : tasks) {
                JCheckBox checkBox = new JCheckBox(task.getTaskName());

                checkBox.setSelected(task.IsCompleted());
                checkBox.setEnabled(!task.IsCompleted());

                checkBox.addActionListener(e -> {
                    if (checkBox.isSelected()) {
                        try {
                            closeTaskController.execute(task.getTaskId());
                            task.setCompleted(true);
                            propertyChange(evt);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                tasksArea.add(checkBox);
            }
        } else if (state instanceof AddTaskState) {
            AddTaskState addTaskState = (AddTaskState) state;
            JCheckBox checkBox = new JCheckBox(addTaskState.getTask_name());
            tasksArea.add(checkBox);
        }
        tasksArea.revalidate();
        tasksArea.repaint();
    }
}


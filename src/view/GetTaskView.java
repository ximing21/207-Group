package view;


import entity.Task;
import interface_adapter.add_task.AddTaskController;
import interface_adapter.add_task.AddTaskState;
import interface_adapter.add_task.AddTaskViewModel;
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
import java.util.ArrayList;

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
        addTaskViewModel.addPropertyChangeListener(this);

        this.tasksArea = new JTextArea(10, 30);
        tasksArea.setEditable(false); // Disable text entry in tasksArea
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
                            String deadlineString = deadlineInputField.getText().trim();

                            // Regular expression for the date format YYYY-MM-DD
                            String dateFormatRegex = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";

                            if (!taskName.isEmpty() && (deadlineString.isEmpty() || deadlineString.matches(dateFormatRegex))) {
                                try {

                                    addTaskController.execute(taskName, deadlineString, getTaskViewModel.getTitleLabel());

                                    // Clear the input field
                                    taskNameInputField.setText("");
                                    deadlineInputField.setText("");

                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else if (!deadlineString.isEmpty() && !deadlineString.matches(dateFormatRegex)) {
                                // Show error message if the deadline format is incorrect
                                JOptionPane.showMessageDialog(null,
                                        "Invalid date or format. Please input a valid date in the format of YYYY-MM-DD.",
                                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
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

    JPanel panel = new JPanel();
    JLabel message = new JLabel();

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

            tasksArea.removeAll();
            for (Task task : tasks) {

                String deadlineText = task.getDeadline() != null && !task.getDeadline().isEmpty()
                        ? " (Deadline: " + task.getDeadline() + ")"
                        : ""; // If there is no deadline, it is not displayed
                String taskText = task.getTaskName() + deadlineText;
                JCheckBox checkBox = new JCheckBox(taskText);

                checkBox.addActionListener(e -> handleCheckBoxAction(task.getTaskId(), checkBox));
                tasksArea.add(checkBox);
            }

            message.setText(getTaskState.getMessage());
            message.setFont(new Font("Serif", Font.ITALIC, 15));
            panel.add(message);
            this.add(panel);

        } else if (state instanceof AddTaskState) {
            AddTaskState addTaskState = (AddTaskState) state;
            String newTaskName = addTaskState.getTask_name();
            String newTaskDeadline = addTaskState.getTaskDeadline();

            // Check if new tasks have deadlines
            String deadlineText = newTaskDeadline != null && !newTaskDeadline.isEmpty()
                    ? " (Deadline: " + newTaskDeadline + ")"
                    : ""; // If there is no deadline, it is not displayed

            if (!newTaskName.isEmpty()) {
                String taskText = newTaskName + deadlineText;
                JCheckBox newTaskCheckBox = new JCheckBox(taskText);
                String newTaskId = addTaskState.getTaskId();
                newTaskCheckBox.addActionListener(e -> handleCheckBoxAction(newTaskId, newTaskCheckBox));
                tasksArea.add(newTaskCheckBox, 0);

            }
        }

        tasksArea.revalidate();
        tasksArea.repaint();
    }

    private void handleCheckBoxAction(String taskId, JCheckBox checkBox) {
        if (checkBox.isSelected()) {
            try {
                closeTaskController.execute(taskId);
                checkBox.setEnabled(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

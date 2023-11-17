package view;

import entity.Project;
import interface_adapter.add_project.AddProjectController;
import interface_adapter.add_project.AddProjectState;
import interface_adapter.add_project.AddProjectViewModel;
import interface_adapter.added_project.AddedProjectState;
import interface_adapter.added_project.AddedProjectViewModel;
import interface_adapter.delete_project.DeleteProjectController;
import interface_adapter.delete_project.DeleteProjectState;
import interface_adapter.delete_project.DeleteProjectViewModel;
import interface_adapter.get_all_projects.GetProjectController;
import interface_adapter.get_all_projects.GetProjectState;
import interface_adapter.get_all_projects.GetProjectViewModel;
import interface_adapter.get_task.GetTaskController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;


public class AddProjectView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "add project";
    private final GetProjectViewModel getProjectViewModel;
    private JList<String> projectList;
    private DefaultListModel<String> listModel;
    private final AddProjectViewModel addProjectViewModel;
    private final JTextField projectnameInputField = new JTextField(15);
    private final AddProjectController addProjectController;
    private final GetProjectController getProjectController;
    private final JButton addProject;
    private final JButton getProject;
    private final AddedProjectViewModel addedProjectViewModel;
    private final GetTaskController getTaskController;
    private final DeleteProjectController deleteProjectController;
    private final DeleteProjectViewModel deleteProjectViewModel;




    public AddProjectView(AddProjectViewModel addProjectViewModel,
                          AddProjectController addProjectController,
                          AddedProjectViewModel addedProjectViewModel,
                          GetProjectViewModel getProjectViewModel,
                          GetProjectController getProjectController,
                          GetTaskController getTaskController, DeleteProjectController deleteProjectController, DeleteProjectViewModel deleteProjectViewModel) {
        this.addProjectViewModel = addProjectViewModel;
        this.addProjectController = addProjectController;
        this.addedProjectViewModel = addedProjectViewModel;

        this.getProjectController = getProjectController;
        this.getProjectViewModel = getProjectViewModel;

        this.getTaskController = getTaskController;
        this.deleteProjectController = deleteProjectController;
        this.deleteProjectViewModel = deleteProjectViewModel;


        getProjectViewModel.addPropertyChangeListener(this);
        addProjectViewModel.addPropertyChangeListener(this);
        addedProjectViewModel.addPropertyChangeListener(this);
        this.deleteProjectViewModel.addPropertyChangeListener(this);

        this.listModel = new DefaultListModel<>();
        this.projectList = new JList<>(listModel);

        JLabel title = new JLabel(AddProjectViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setForeground(Color.darkGray);

        LabelTextPanel projectnameInfo = new LabelTextPanel(
                new JLabel(AddProjectViewModel.PROJECT_NAME_LABEL), projectnameInputField);

        JPanel inputPanel = new JPanel();
        Font font = new Font("SansSerif", Font.PLAIN,15);
        addProject = new JButton(AddProjectViewModel.ADD_PROJECT_BUTTON_LABEL);
        addProject.setFont(font);

        inputPanel.add(projectnameInfo);
        inputPanel.add(addProject, BorderLayout.AFTER_LINE_ENDS);

        JPanel button = new JPanel();
        getProject = new JButton(AddProjectViewModel.GET_PROJECT_BUTTON_LABEL);
        getProject.setFont(font);
        button.add(getProject);

        getProject.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(getProject)) {
                            getProjectController.execute();
                        }
                    }
                }
        );

        addProject.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(addProject)) {
                            String projectName = projectnameInputField.getText();
                            if (!projectName.isEmpty()) {
                                try {
                                    AddProjectState currentState = addProjectViewModel.getState();
                                    addProjectController.execute(currentState.getProject_name());
                                    projectnameInputField.setText("");

                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
//                            JOptionPane.showMessageDialog(parent, currentState.getProject_name() +"successfully created");
                        }

                    }
                }
        );
        projectnameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        AddProjectState currentState = addProjectViewModel.getState();
                        String text = projectnameInputField.getText() + e.getKeyChar();
                        currentState.setProject_name(text);
                        addProjectViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );

        projectList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    String selectedProjectWithCount = projectList.getSelectedValue();
                    String selectedProjectName = selectedProjectWithCount.split(" \\(")[0];
                    getTaskController.execute(selectedProjectName);
                }
            }
        });

        projectList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (SwingUtilities.isRightMouseButton(evt)) {
                    String selectedProjectWithCount = projectList.getSelectedValue();
                    String selectedProjectName = selectedProjectWithCount.split(" \\(")[0];
                    Boolean result = deleteConfirmation(selectedProjectName);
                    if (result == true) {
                        AddProjectView.this.deleteProjectController.execute(selectedProjectName);
                    }
                }
            }
        });


        JScrollPane scrollPane = new JScrollPane(projectList);
        scrollPane.setPreferredSize(new Dimension(80,100));

        this.setPreferredSize(new Dimension(850, 300));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(inputPanel);
        this.setBackground(Color.ORANGE);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(button);
    }

    private Boolean deleteConfirmation(String name) {
        int confirm = JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete the project '" + name + "'?",
                            "Delete Project",
                            JOptionPane.YES_NO_OPTION
                    );
        if (confirm == JOptionPane.YES_OPTION) {
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object state = evt.getNewValue();
        if (state instanceof AddProjectState) {
            AddProjectState addProjectState = (AddProjectState) state;
            if (addProjectState.getProject_nameError() != null) {
                JOptionPane.showMessageDialog(this, addProjectState.getProject_nameError());
            }
        } else if (state instanceof AddedProjectState) {
            AddedProjectState addedProjectState = (AddedProjectState) state;
            String projectNameWithCount = addedProjectState.getProjectname() + " (# tasks: 0)";
            listModel.addElement(projectNameWithCount);
            JOptionPane.showMessageDialog(this, addedProjectState.getProjectname() + " successfully created");
        } else if (state instanceof GetProjectState) {
            GetProjectState getProjectState = (GetProjectState) state;
            List<Project> projects = List.of(getProjectState.getProjects());
            listModel.clear();
            for (Project project : projects) {
                String displayText = project.getName() + " (# tasks:" + project.getTaskCount() + ")";
                listModel.addElement(displayText);
            }
        } else if (state instanceof DeleteProjectState) {
            DeleteProjectState deleteProjectState = (DeleteProjectState) state;
            String projectName = deleteProjectState.getProject_name();
            Integer count = deleteProjectState.getCount();
            String projectNameWithCount = projectName + " (# tasks: " + count.toString() +")";
            listModel.removeElement(projectNameWithCount);
        }
    }
}

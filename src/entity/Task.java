package entity;

import java.time.LocalDateTime;

public class Task implements TaskInterface{
    private String taskName;
    private LocalDateTime deadline;
    private String priority;
    private Boolean isCompleted;
    private LocalDateTime reminderTime;


    public Task(String taskName, LocalDateTime deadline, String priority,
                Boolean isCompleted, LocalDateTime reminderTime) {
        this.taskName = taskName;
        this.deadline = deadline;
        this.priority = priority;
        this.isCompleted = isCompleted;
        this.reminderTime = reminderTime;
    }

    @Override
    public String getName() {
        return taskName;
    }

    @Override
    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String getPriority() {
        return priority;
    }

    @Override
    public Boolean getIsCompleted() {
        return isCompleted;
    }

    @Override
    public LocalDateTime getReminderTime() {
        return reminderTime;
    }
}

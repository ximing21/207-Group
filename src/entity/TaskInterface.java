package entity;

import java.time.LocalDateTime;

public interface TaskInterface {

    String getName();

    LocalDateTime getDeadline();

    String getPriority();

    Boolean getIsCompleted();

    LocalDateTime getReminderTime();
}

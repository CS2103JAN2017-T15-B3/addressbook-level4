package seedu.taskmanager.model;

public interface TaskNotifier {

    /**
     * Sets a new period in which a task is to be notified to the user.
     *
     */
    void setNotification(String duration);

}

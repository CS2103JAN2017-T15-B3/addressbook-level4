package seedu.taskmanager.model.task;

public class TaskUtil {

    public static boolean isFloating(ReadOnlyTask task) {
        return (task.isFloating());
    }

    public static boolean isDeadline(ReadOnlyTask task) {
        return (task.isDeadline());
    }

    public static boolean isEvent(ReadOnlyTask task) {
        return (task.isEvent());
    }
}

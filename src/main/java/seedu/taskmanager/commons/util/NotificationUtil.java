package seedu.taskmanager.commons.util;

import java.util.Date;

import seedu.taskmanager.model.TaskNotifierManager;

/**
 * A util class to access attributes stored in TaskNotifierManager
 */
public class NotificationUtil {

    public static Date getNotificationDate() {
        return TaskNotifierManager.dateHelper;
    }

}

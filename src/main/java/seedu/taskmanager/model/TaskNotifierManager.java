package seedu.taskmanager.model;

import java.util.Date;

import seedu.taskmanager.commons.exceptions.IllegalValueException;
import seedu.taskmanager.logic.parser.DateTimeUtil;

public class TaskNotifierManager implements TaskNotifier {

    private static final String DEFAULT_NOTIFICATION = "3 days";

    public UserPrefs userprefs;

    public static String notificationSetting = DEFAULT_NOTIFICATION;
    public static Date dateHelper;

    public TaskNotifierManager(UserPrefs userprefs) {
        this.userprefs = userprefs;
        setNotification(userprefs.getNotificationSetting());
    }

    @Override
    public void setNotification(String duration) {
        notificationSetting = duration;
        userprefs.setNotificationSettings(duration);

        try {
            dateHelper = setDateHelper();
        } catch (IllegalValueException ive) {}

    }

    private Date setDateHelper() throws IllegalValueException {
        return DateTimeUtil.parseDateTime(notificationSetting).getTaskDate();
    }

}

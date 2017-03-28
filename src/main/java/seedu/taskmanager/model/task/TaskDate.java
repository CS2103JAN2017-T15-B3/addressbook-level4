package seedu.taskmanager.model.task;

import java.util.Calendar;
import java.util.Date;

import seedu.taskmanager.logic.parser.DateTimeUtil;

/**
 * Represents a Task's name in the task manager. Guarantees: immutable; is valid
 * as declared in {@link #isValidName(String)}
 */
public class TaskDate {

    private final Date taskDate;

    public TaskDate(Date date) {
        this.taskDate = date;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public String getOnlyDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(taskDate);

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        return Integer.toString(day) + Integer.toString(month) + Integer.toString(year);
    }

    @Override
    public String toString() {
        return DateTimeUtil.getStringFromDate(taskDate);
    }

    @Override
    public boolean equals(Object other) {

        return other == this // short circuit if same object
                || (other instanceof TaskDate // instanceof handles nulls
                        && this.taskDate.equals(((TaskDate) other).taskDate)); // state
                                                                               // check
    }

    @Override
    public int hashCode() {
        return taskDate.hashCode();
    }

}

// @@author A0130277L

package seedu.taskmanager.model.task;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import seedu.taskmanager.logic.parser.DateTimeUtil;

/**
 * Represents a Task's name in the task manager. Guarantees: immutable; is valid as declared in
 * {@link #isValidName(String)}
 */
public class TaskDate {

    private final Date taskDate;
    private final boolean hasTime;

    public TaskDate(Date date, boolean hasTime) {
        assertNotNull(date);
        this.taskDate = date;
        this.hasTime = hasTime;
    }

    public boolean hasTime() {
        return hasTime;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    // toString method for taskDate
    // @return date with no time element if there is no explicit time
    @Override
    public String toString() {
        String toBeDisplayed;
        if (!hasTime) {
            toBeDisplayed = DateTimeUtil.getOnlyDateStringFromDate(taskDate);
        } else {
            toBeDisplayed = DateTimeUtil.getStringFromDate(taskDate);
        }
        return toBeDisplayed;
    }

    @Override
    public boolean equals(Object other) {

        return other == this // short circuit if same object
                || (other instanceof TaskDate // instanceof handles nulls
                        && this.taskDate.equals(((TaskDate) other).taskDate)); // state
        // check
    }

}


package seedu.taskmanager.model.task;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import seedu.taskmanager.commons.util.CollectionUtil;
import seedu.taskmanager.commons.util.NotificationUtil;
import seedu.taskmanager.model.tag.UniqueTagList;

/**
 * Represents a Task in the task manager. Guarantees: details are present and not null, field values are validated.
 */
public class Task implements ReadOnlyTask {

    private Name name;
    // @@author A0140417R
    private Optional<TaskDate> startDate, endDate;
    private UniqueTagList tags;
    private boolean isDoneStatus;
    private boolean isDueSoonStatus;

    public Task(Name name, TaskDate startDate, TaskDate endDate, UniqueTagList tags, boolean status) {
        assert !CollectionUtil.isAnyNull(name, tags);
        this.name = name;
        this.startDate = Optional.ofNullable(startDate);
        this.endDate = Optional.ofNullable(endDate);
        this.tags = new UniqueTagList(tags); // protect internal tags from
                                             // changes in the arg list
        this.isDoneStatus = status;
        setDueSoonStatus();
    }

    // @@author A0140538J
    public Task(Name name, TaskDate startDate, TaskDate endDate, UniqueTagList tags) {
        this(name, startDate, endDate, tags, false);
        setDueSoonStatus();
    }

    public Task(Name name, UniqueTagList tags) {
        this(name, null, null, tags, false);
        setDueSoonStatus();
    }

    public Task(Name name, Optional<TaskDate> startDate, Optional<TaskDate> endDate, UniqueTagList tags) {
        this(name, startDate.orElse(null), endDate.orElse(null), tags, false);
        setDueSoonStatus();
    }

    /**
     * Creates a copy of the given ReadOnlyTask.
     */
    public Task(ReadOnlyTask source) {
        this(source.getName(), source.getStartDate(), source.getEndDate(), source.getTags(), source.isDone());
        setDueSoonStatus();
    }

    public void setName(Name name) {
        assert name != null;
        this.name = name;
    }

    @Override
    public Name getName() {
        return name;
    }

    // @@author A0140417R
    @Override
    public TaskDate getStartDate() {
        return startDate.orElse(null);
    }

    @Override
    public TaskDate getEndDate() {
        return endDate.orElse(null);
    }

    public void setStartDate(TaskDate taskDate) {
        this.startDate = Optional.ofNullable(taskDate);
    }

    public void setEndDate(TaskDate taskDate) {
        this.endDate = Optional.ofNullable(taskDate);
    }

    public void removeStartDate() {
        this.startDate = Optional.empty();
    }

    public void removeEndDate() {
        this.endDate = Optional.empty();
    }

    @Override
    public boolean hasStartDate() {
        return startDate.isPresent();
    }

    @Override
    public boolean hasEndDate() {
        return endDate.isPresent();
    }

    @Override
    public boolean isFloating() {
        return !hasStartDate() && !hasEndDate();
    }

    @Override
    public boolean isDeadline() {
        return !hasStartDate() && hasEndDate();
    }

    @Override
    public boolean isEvent() {
        return hasStartDate() && hasEndDate();
    }

    public boolean isValidTask() {
        return isFloating() || isDeadline()
                || (isEvent() && startDate.get().getTaskDate().before(endDate.get().getTaskDate()));
    }
    // @@author

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    /**
     * Replaces this task's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }

    /**
     * Updates this task with the details of {@code replacement}.
     */
    public void resetData(ReadOnlyTask replacement) {
        assert replacement != null;

        this.setName(replacement.getName());
        this.setStartDate(replacement.getStartDate());
        this.setEndDate(replacement.getEndDate());
        this.setTags(replacement.getTags());
        this.setDoneStatus(replacement.isDone());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                        && this.isSameStateAs((ReadOnlyTask) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing
        // your own
        return Objects.hash(name, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }

    // @@author A0140538J
    @Override
    public boolean isDone() {
        return isDoneStatus;
    }

    public void setDoneStatus(boolean status) {
        this.isDoneStatus = status;
    }

    public void setDueSoonStatus() {
        Date notificationDate = NotificationUtil.getNotificationDate();

        if (this.hasStartDate()) {
            if (getStartDate().getTaskDate().before(notificationDate)) {
                this.isDueSoonStatus = true;
                return;
            }
        }

        if (this.hasEndDate()) {
            if (getEndDate().getTaskDate().before(notificationDate)) {
                this.isDueSoonStatus = true;
                return;
            }
        }

        this.isDueSoonStatus = false;
    }

    @Override
    public boolean isDueSoon() {
        return isDueSoonStatus;
    }

}

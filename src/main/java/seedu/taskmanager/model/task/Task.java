package seedu.taskmanager.model.task;

import java.util.Objects;
import java.util.Optional;

import seedu.taskmanager.commons.util.CollectionUtil;
import seedu.taskmanager.model.tag.UniqueTagList;

/**
 * Represents a Task in the task manager. Guarantees: details are present and
 * not null, field values are validated.
 */
public class Task implements ReadOnlyTask {

    private Name name;
    private Optional<TaskDate> startDate, endDate;
    private UniqueTagList tags;
    private boolean isDone;

    /**
     * Every field must be present and not null.
     */

    public Task(Name name, Optional<TaskDate> startDate, Optional<TaskDate> endDate, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(name, tags);
        this.name = name;
        this.startDate = Optional.ofNullable(startDate);
        this.endDate = Optional.ofNullable(endDate);
        this.tags = new UniqueTagList(tags); // protect internal tags from
                                             // changes in the arg list

    }

    public Task(Name name, UniqueTagList tags) throws IllegalValueException {
        this.name = name;
        this.startDate = Optional.empty();
        this.endDate = Optional.empty();
        this.tags = new UniqueTagList(tags); // protect internal tags from
                                             // changes in the arg list
        this.isDone = false;
    }

    /**

     * Creates task based on optional startDate and endDate
     *
     * @param name
     * @param startDate
     * @param endDate
     * @param tags
     * @throws IllegalValueException
     */
    public Task(Name name, Optional<TaskDate> startDate, Optional<TaskDate> endDate, UniqueTagList tags)
            throws IllegalValueException {
        this(name, startDate.orElse(null), endDate.orElse(null), tags);
    }

    /**

     * Creates a copy of the given ReadOnlyTask.
     */
    public Task(ReadOnlyTask source) {
        this.name = source.getName();
        this.startDate = Optional.of(source.getStartDate());
        this.endDate = Optional.of(source.getEndDate());
        this.tags = source.getTags();
    }

    public void setName(Name name) {
        assert name != null;
        this.name = name;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override

    public TaskDate getStartDate() {
        assert startDate.isPresent();
        return startDate.get();
    }

    @Override
    public TaskDate getEndDate() {
        assert endDate.isPresent();
        return endDate.get();
    }

    public void setStartDate(TaskDate taskDate) {
        assert taskDate != null;
        this.startDate = Optional.of(taskDate);
    }

    public void setEndDate(TaskDate taskDate) {
        assert taskDate != null;
        this.endDate = Optional.of(taskDate);
    }

    public void removeStartDate() {
        this.startDate = Optional.empty();
    }

    public void removeEndDate() {
        this.startDate = Optional.empty();
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

    @Override
    public boolean isDone() {
        return isDone;
    }

}

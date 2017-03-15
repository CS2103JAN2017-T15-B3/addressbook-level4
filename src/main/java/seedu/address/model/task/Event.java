package seedu.address.model.task;

import java.util.Objects;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.tag.UniqueTagList;
import seedu.address.model.task.Task;

/**
 * Represents an Event in the TaskManager.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Event extends Task implements ReadOnlyEvent {

	private Name name;
	private TaskDate startDate;
	private TaskDate endDate;

    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
    public Event(Name name, TaskDate startDate, TaskDate endDate, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(name, tags);
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Creates a copy of the given ReadOnlyEvent.
     */
    public Event(ReadOnlyEvent source) {
        this(source.getName(), source.getStartDate(), source.getEndDate(), source.getTags());
    }

    @Override
    public Name getName() {
        return name;
    }
    
    @Override
    public void setName(Name name) {
        assert name != null;
        this.name = name;
    }
    
    @Override
    public TaskDate getStartDate() {
    	return startDate;
    }
    
    @Override
    public TaskDate getEndDate() {
    	return endDate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyEvent // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyEvent) other));
    }
    
    @Override
    public String toString() {
        return getAsText();
    }

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    /**
     * Replaces this person's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }

    /**
     * Updates this person with the details of {@code replacement}.
     */
    public void resetData(ReadOnlyEvent replacement) {
        assert replacement != null;

        this.setName(replacement.getName());
        this.setTags(replacement.getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, tags);
    }
}

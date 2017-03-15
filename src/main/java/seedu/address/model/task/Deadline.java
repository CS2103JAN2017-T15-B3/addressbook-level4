package seedu.address.model.task;

import java.util.Objects;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.tag.UniqueTagList;
import seedu.address.model.task.Task;

/**
 * Represents a Deadline in the TaskManager.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Deadline extends Task implements ReadOnlyDeadline {

	private Name name;
	private TaskDate endDate;
    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
    public Deadline(Name name, TaskDate endDate, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(name, tags);
        this.name = name;
        this.endDate = endDate;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Creates a copy of the given ReadOnlyDeadline.
     */
    public Deadline(ReadOnlyDeadline source) {
        this(source.getName(), source.getEndDate(), source.getTags());
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
    public TaskDate getEndDate() {
    	return endDate;
    }
    
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyDeadline // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyDeadline) other));
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
    public void resetData(ReadOnlyDeadline replacement) {
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

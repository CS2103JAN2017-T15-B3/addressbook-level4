package seedu.address.model.task;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.parser.ArgumentTokenizer.Prefix;

/**
 * Represents a FloatingTask in the TaskManager.
 * Guarantees: details are present and not null, field values are validated.
 */
public class FloatingTask implements ReadOnlyFloatingTask{

    private Name name;

    private Map<Prefix, List<String>> tags;

    /**
     * Every field must be present and not null.
     */
    public FloatingTask(Name name, Map<Prefix, List<String>> tags) {
        assert !CollectionUtil.isAnyNull(name, tags);
        this.name = name;
        this.tags = tags;
    }

    /**
     * Creates a copy of the given ReadOnlyFloatingTask.
     */
    public FloatingTask(ReadOnlyFloatingTask source) {
        this(source.getName(), source.getTags());
    }

    @Override
    public Name getName() {
        return name;
    }
    
    public void setName(Name name) {
        assert name != null;
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyFloatingTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyFloatingTask) other));
    }
    
    @Override
    public String toString() {
        return getAsText();
    }

    public Map<Prefix, List<String>> getTags() {
        return tags;
    }

    /**
     * Replaces this person's tags with the tags in the argument tag list.
     */
    public void setTags(Map<Prefix, List<String>> replacement) {
        tags = replacement;
    }

    /**
     * Updates this person with the details of {@code replacement}.
     */
    public void resetData(ReadOnlyFloatingTask replacement) {
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

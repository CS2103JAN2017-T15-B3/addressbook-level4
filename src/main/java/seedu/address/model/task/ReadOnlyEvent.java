package seedu.address.model.task;

/**
 * A read-only immutable interface for an Event in the TaskManager.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyEvent extends ReadOnlyTask {
	
	TaskDate getStartDate();
	
	TaskDate getEndDate();

    /**
     * Returns true if both have the same state. (interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyEvent other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getName().equals(this.getName())
                && other.getStartDate().equals(this.getStartDate())
                && other.getEndDate().equals(this.getEndDate())// state checks here onwards
                );
    }

}

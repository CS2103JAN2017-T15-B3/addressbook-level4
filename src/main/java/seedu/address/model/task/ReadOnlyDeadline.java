package seedu.address.model.task;

/**
 * A read-only immutable interface for a Deadline in the TaskManager.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyDeadline extends ReadOnlyFloatingTask {
	
	TaskDate getEndDate();

    /**
     * Returns true if both have the same state. (interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyDeadline other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getName().equals(this.getName())
                && other.getEndDate().equals(this.getEndDate())// state checks here onwards
                );
    }

}

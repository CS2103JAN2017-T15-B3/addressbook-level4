package seedu.address.model.task;

/**
 * Represents a task. It can be a floating task, an event or a deadline.
 */

public abstract class Task {
	
	protected Name name;
	
	public Name getName() {
		return name;
	}
	
	public void setName(Name name) {
		assert name != null;
		this.name= name;
	}
	
	public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Task // instanceof handles nulls
                && this.equals((Task) other));
    }
	
	public String toString() {
		return name.fullName;
	}
}
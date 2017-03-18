package seedu.address.model.person;

import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.tag.UniqueTagList;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Person implements ReadOnlyPerson {
    
    public static final TaskDate NO_DATE = null;

    private Name name;
    private TaskDate startDate, endDate;
    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
    
    public Person(Name name, TaskDate startDate, TaskDate endDate, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(name, tags);
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }
    
    
    public Person(Name name, UniqueTagList tags) throws IllegalValueException{
        this.name = name;
        this.startDate = new DummyStartTaskDate();
        this.endDate = new DummyEndTaskDate();
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }
    /**
     * Creates person based on optional startDate and endDate
     * @param name
     * @param startDate
     * @param endDate
     * @param tags
     */
    public Person(Name name, Optional<TaskDate> startDate, Optional<TaskDate> endDate, UniqueTagList tags) {
        this(name,startDate.orElse(NO_DATE),endDate.orElse(NO_DATE),tags);
    }
    
    /**
     * Creates a copy of the given ReadOnlyPerson.
     */
    public Person(ReadOnlyPerson source) {
        this.name = source.getName();
        this.startDate = source.getStartDate();
        this.endDate = source.getEndDate();
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
    public TaskDate getStartDate(){
        return startDate;
    }
    
    @Override
    public TaskDate getEndDate(){
        return endDate;
    }
    
    public void setStartDate(TaskDate taskDate){
        this.startDate = taskDate;
    }
    
    public void setEndDate(TaskDate taskDate){
        this.endDate = taskDate;
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
    public void resetData(ReadOnlyPerson replacement) {
        assert replacement != null;

        this.setName(replacement.getName());
        this.setTags(replacement.getTags());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyPerson // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyPerson) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}

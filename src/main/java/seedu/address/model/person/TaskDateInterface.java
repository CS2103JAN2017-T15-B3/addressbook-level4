package seedu.address.model.person;

import java.util.Date;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.DateTimeUtil;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public interface TaskDateInterface {

    public String toString();
    public boolean equals(Object other);
    public int hashCode();

}

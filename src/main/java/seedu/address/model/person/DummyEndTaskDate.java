package seedu.address.model.person;

import seedu.address.logic.parser.DateTimeUtil;

/**
 * Represents a dummy end date as a filler if no end date is specified.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class DummyEndTaskDate extends TaskDate {
    public static final String DUMMY_END_DATE_STRING = "23 August 1994, 06:54 PM";
    
    public DummyEndTaskDate() {
        super(DateTimeUtil.parseDateTime(DUMMY_END_DATE_STRING));
    }

    @Override
    public String toString() {
        return DUMMY_END_DATE_STRING;
    }
}

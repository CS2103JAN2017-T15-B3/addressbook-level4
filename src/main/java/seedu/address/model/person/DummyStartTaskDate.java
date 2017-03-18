package seedu.address.model.person;

import seedu.address.logic.parser.DateTimeUtil;

/**
 * Represents a dummy start date as a filler if no start date is specified.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class DummyStartTaskDate extends TaskDate {
    public static final String DUMMY_START_DATE_STRING = "26 March 1992, 12:12 PM";
    
    public DummyStartTaskDate() {
        super(DateTimeUtil.parseDateTime(DUMMY_START_DATE_STRING));
    }

    @Override
    public String toString() {
        return DUMMY_START_DATE_STRING;
    }
}

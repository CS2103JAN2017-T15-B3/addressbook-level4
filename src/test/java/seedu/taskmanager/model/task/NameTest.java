package seedu.taskmanager.model.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NameTest {

    @Test
    public void isValidName() {
        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        // assertFalse(Name.isValidName(" ")); // spaces only; not a good test
        // as white spaces will be removed

        // valid name
        assertTrue(Name.isValidName("meeting coffee")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("birthday the 2nd")); // alphanumeric
                                                       // characters
        assertTrue(Name.isValidName("Capital Markets")); // with capital letters
        assertTrue(Name.isValidName("Meeting At USA Gordon Ramsay Restaurant")); // long
                                                                        // names
    }
}

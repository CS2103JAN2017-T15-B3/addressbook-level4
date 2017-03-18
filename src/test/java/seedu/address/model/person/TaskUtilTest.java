package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.DateTimeUtil;
import seedu.address.model.tag.UniqueTagList;

public class TaskUtilTest {
    public void isFloatingTask_floatingTask_returnTrue() throws IllegalValueException{
        Name name = new Name("apple");
        TaskDate start = null;
        TaskDate end = null;
        UniqueTagList tags = null;
        
        assertTrue(TaskUtil.isFloating(new Person(name,start,end,tags)));
    }
}

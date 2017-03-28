package seedu.taskmanager.model.task;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import seedu.taskmanager.commons.exceptions.IllegalValueException;
import seedu.taskmanager.model.tag.UniqueTagList;

public class TaskUtilTest {
    public void isFloatingTask_floatingTask_returnTrue() throws IllegalValueException {
        Name name = new Name("apple");
        TaskDate start = null;
        TaskDate end = null;
        UniqueTagList tags = null;

        assertTrue(TaskUtil.isFloating(new Task(name, Optional.of(start), Optional.of(end), tags)));
    }
}

package seedu.taskmanager.logic;

import static seedu.taskmanager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import org.junit.Test;

import seedu.taskmanager.logic.commands.SetNotificationCommand;
import seedu.taskmanager.logic.parser.DateTimeUtil;
import seedu.taskmanager.model.TaskManager;
import seedu.taskmanager.model.task.Task;

// @@author A0140538J
public class LogicSetNotificationCommandTest extends LogicManagerTest {

    @Test
    public void execute_set_emptyString() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetNotificationCommand.MESSAGE_USAGE);
        String invalidCommand = "set";
        assertCommandFailure(invalidCommand, expectedMessage);
    }

    @Test
    public void execute_set_invalidArg() {
        String expectedMessage = DateTimeUtil.INVALID_DATE_FORMAT;
        String invalidCommand = "set abcdefgh";
        assertCommandFailure(invalidCommand, expectedMessage);
    }

    @Test
    public void execute_set_invalidDurationFormat() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetNotificationCommand.MESSAGE_USAGE);
        String invalidCommand = "set 1 week ago";
        assertCommandFailure(invalidCommand, expectedMessage);
    }

    @Test
    public void execute_set_successful() throws Exception {
        TestDataHelper helper = new TestDataHelper();

        List<Task> expectedList = helper.generateTaskList();
        TaskManager expectedTM = helper.generateTaskManager(expectedList);


        String expectedMessage = String.format(SetNotificationCommand.MESSAGE_SUCCESS, "1 day");
        String validCommand = "set 1 day";
        assertCommandSuccess(validCommand, expectedMessage, expectedTM, expectedList);
    }

}

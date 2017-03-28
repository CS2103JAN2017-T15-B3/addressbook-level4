package seedu.taskmanager.logic;

import static seedu.taskmanager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.Test;

import seedu.taskmanager.logic.commands.AddCommand;
import seedu.taskmanager.logic.parser.DateTimeUtil;
import seedu.taskmanager.model.TaskManager;
import seedu.taskmanager.model.tag.Tag;
import seedu.taskmanager.model.task.Task;

public class LogicAddCommandTest extends LogicManagerTest {

    @Test
    public void execute_add_invalidArgsFormat() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
        assertCommandFailure("add", expectedMessage);
        // Check if only startDate is present without endDate
        assertCommandFailure("add Meeting s/1/1/2018", expectedMessage);
    }

    @Test
    public void execute_add_invalidTaskData() {
        assertCommandFailure("add Valid Name t/invalid_-[.tag", Tag.MESSAGE_TAG_CONSTRAINTS);
        assertCommandFailure("add Meeting s/invalid date-time e/invalid date-time", DateTimeUtil.INVALID_DATE_FORMAT);
    }

    @Test
    public void execute_add_successful() throws Exception {
        // setup expectations
        TestDataHelper helper = new TestDataHelper();
        Task toBeAdded = helper.meeting();
        TaskManager expectedAB = new TaskManager();
        expectedAB.addTask(toBeAdded);

        // execute command and verify result
        assertCommandSuccess(helper.generateAddCommand(toBeAdded), String.format(AddCommand.MESSAGE_SUCCESS, toBeAdded),
                expectedAB, expectedAB.getTaskList());
    }

    @Test
    public void execute_addDuplicate_notAllowed() throws Exception {
        // setup expectations
        TestDataHelper helper = new TestDataHelper();
        Task toBeAdded = helper.meeting();

        // setup starting state
        model.addTask(toBeAdded); // task already in internal task manager

        // execute command and verify result
        assertCommandFailure(helper.generateAddCommand(toBeAdded), AddCommand.MESSAGE_DUPLICATE_TASK);

    }
}

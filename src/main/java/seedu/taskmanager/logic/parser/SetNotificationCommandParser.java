
package seedu.taskmanager.logic.parser;

import static org.junit.Assert.assertNotNull;

import static seedu.taskmanager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskmanager.commons.util.CommonStringUtil.EMPTY_STRING;

import seedu.taskmanager.commons.exceptions.IllegalValueException;
import seedu.taskmanager.logic.commands.Command;
import seedu.taskmanager.logic.commands.IncorrectCommand;
import seedu.taskmanager.logic.commands.SetNotificationCommand;
import seedu.taskmanager.model.task.TaskDate;

// @@author A0140538J
/**
 * Parses input arguments and creates a new SetNotificationCommand object
 */
public class SetNotificationCommandParser {

    private static final String CURRENT_TIME = "now";

    /**
     * Parses the given {@code String} of arguments in the context of the SetNotificationCommand and returns a
     * SetNotificationCommand object for execution.
     */
    public Command parse(String arg) {

        assertNotNull(arg);

        String modifiedArg = arg.trim();

        if (modifiedArg.equals(EMPTY_STRING)) {
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetNotificationCommand.MESSAGE_USAGE));
        }

        TaskDate currentDate;
        TaskDate date;

        try {
            currentDate = DateTimeUtil.parseDateTime(CURRENT_TIME);
            date = DateTimeUtil.parseDateTime(modifiedArg);
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }

        if (date.getTaskDate().before(currentDate.getTaskDate())) {
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetNotificationCommand.MESSAGE_USAGE));
        }

        return new SetNotificationCommand(modifiedArg);
    }

}

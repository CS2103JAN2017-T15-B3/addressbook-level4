package seedu.taskmanager.logic.parser;

import static seedu.taskmanager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.taskmanager.commons.exceptions.IllegalValueException;
import seedu.taskmanager.logic.commands.Command;
import seedu.taskmanager.logic.commands.IncorrectCommand;
import seedu.taskmanager.logic.commands.SetNotificationCommand;
import seedu.taskmanager.model.task.TaskDate;

// @@author A0140538J
public class SetNotificationCommandParser {

    public static final String EMPTY_STRING = "";
    public static final String CURRENT_TIME = "now";

    public Command parse(String arg) {

        assert arg != null;

        String modifiedArg = arg.trim();

        if (modifiedArg.equals(EMPTY_STRING)) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetNotificationCommand.MESSAGE_USAGE));
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
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetNotificationCommand.MESSAGE_USAGE));
        }

        return new SetNotificationCommand(modifiedArg);
    }

}

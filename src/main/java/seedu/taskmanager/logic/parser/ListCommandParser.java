
package seedu.taskmanager.logic.parser;

import static seedu.taskmanager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskmanager.commons.util.CommonStringUtil.DEADLINE_STRING;
import static seedu.taskmanager.commons.util.CommonStringUtil.EVENT_STRING;
import static seedu.taskmanager.commons.util.CommonStringUtil.FLOATING_TASK_STRING;

import seedu.taskmanager.logic.commands.Command;
import seedu.taskmanager.logic.commands.IncorrectCommand;
import seedu.taskmanager.logic.commands.ListCommand;

// @@author A0140538J
/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser {

    /**
     * Parses the given argument in the context of the ListCommand and returns an ListCommand object for execution.
     */
    public Command parse(String arg) {

        String modifiedArg = arg.trim();

        ListArgumentsUtil argumentChecker = new ListArgumentsUtil(modifiedArg);
        if (!argumentChecker.acceptedWords.contains(modifiedArg) && !argumentChecker.isDate) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        if (argumentChecker.acceptedWords.contains(modifiedArg)) {
            return new ListCommand(modifiedArg);
        }

        if (modifiedArg.contains(FLOATING_TASK_STRING)) {
            return new IncorrectCommand(ListCommand.MESSAGE_FLOATING_NO_DATE);
        } else if (modifiedArg.contains(DEADLINE_STRING)) {
            return new ListCommand(formatArgs(modifiedArg, DEADLINE_STRING));
        } else if (modifiedArg.contains(EVENT_STRING)) {
            return new ListCommand(formatArgs(modifiedArg, EVENT_STRING));
        }

        return new ListCommand(modifiedArg);

    }

    /**
     * Formats a String of arguments into two different filters. First filter is the taskType string , second filter is
     * the date string. Stores it in an array to be sent for filtering.
     */
    private String[] formatArgs(String twoArgs, String taskType) {
        String[] formattedArgs = new String[2];
        String[] helper = twoArgs.split(taskType);

        if (helper[0].isEmpty()) {
            formattedArgs[0] = taskType;
            formattedArgs[1] = helper[1].trim();
        } else {
            formattedArgs[0] = taskType;
            formattedArgs[1] = helper[0].trim();
        }

        return formattedArgs;
    }

}

package seedu.taskmanager.logic.parser;

import static seedu.taskmanager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.taskmanager.commons.exceptions.IllegalValueException;
import seedu.taskmanager.logic.commands.Command;
import seedu.taskmanager.logic.commands.IncorrectCommand;
import seedu.taskmanager.logic.commands.ListCommand;

public class ListCommandParser {

    /**
     * Parses the given argument in the context of the ListCommand and returns
     * an ListCommand object for execution.
     *
     * @throws IllegalValueException
     */
    public Command parse(String arg) {

        String modifiedArg = arg.trim();

        ListArguments argumentChecker = new ListArguments(modifiedArg);
        if (!argumentChecker.acceptedWords.contains(modifiedArg) && argumentChecker.isDate == false) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        if (argumentChecker.acceptedWords.contains(modifiedArg)) {
        	return new ListCommand(modifiedArg);
        }

        if (modifiedArg.contains(ListArguments.FLOATING_TASK_STRING)) {
        	return new ListCommand(formatArgs(modifiedArg, ListArguments.FLOATING_TASK_STRING));
        } else if (modifiedArg.contains(ListArguments.DEADLINE_STRING)) {
        	return new ListCommand(formatArgs(modifiedArg, ListArguments.DEADLINE_STRING));
        } else if (modifiedArg.contains(ListArguments.EVENT_STRING)) {
        	return new ListCommand(formatArgs(modifiedArg, ListArguments.EVENT_STRING));
        }

        return new ListCommand(modifiedArg);

    }

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

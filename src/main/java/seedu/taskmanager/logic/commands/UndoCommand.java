
package seedu.taskmanager.logic.commands;

import static org.junit.Assert.assertNotNull;

import seedu.taskmanager.logic.commands.exceptions.CommandException;

// @@author A0140417R
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undo the previous command.\n";

    public static final String MESSAGE_SUCCESS = "Undo successful";
    public static final String MESSAGE_NO_PREVIOUS_STATE = "No previous state found.";
    public static final String MESSAGE_NOT_LISTED = "Invalid input" + MESSAGE_USAGE;

    @Override
    public CommandResult execute() throws CommandException {
        assertNotNull(model);
        try {
            model.loadPreviousState();
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_NO_PREVIOUS_STATE);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean mutatesTaskManager() {
        return false;
    }
}

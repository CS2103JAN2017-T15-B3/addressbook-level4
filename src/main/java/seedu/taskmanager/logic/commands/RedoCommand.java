
package seedu.taskmanager.logic.commands;

import static org.junit.Assert.assertNotNull;

import seedu.taskmanager.logic.commands.exceptions.CommandException;

// @@author A0140417R
/**
 * Re-do command that only can be performed after an undo command.
 * @author zeticous
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Repeat the command before previous undo commadn\n";
    public static final String MESSAGE_SUCCESS = "Redo successful";
    public static final String MESSAGE_NO_NEW_STATE = "No new state found.";

    @Override
    public CommandResult execute() throws CommandException {
        assertNotNull(model);
        try {
            model.loadNextState();
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_NO_NEW_STATE);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean mutatesTaskManager() {
        return false;
    }
}


package seedu.taskmanager.logic.commands;

// @@author A0140417R
/**
 * Changes the file path to the indicated directory
 */
public class FilepathCommand extends Command {

    public static final String COMMAND_WORD = "filepath";
    public static final String MESSAGE_SUCCESS = "File path changed to %1$s";
    private final String filePath;

    public FilepathCommand(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute() {
        assert model != null;
        assert filePath != null;

        model.changeFilePath(filePath);
        return new CommandResult(String.format(MESSAGE_SUCCESS, filePath));
    }


    @Override
    public boolean mutatesTaskManager() {
        return false;
    }
}

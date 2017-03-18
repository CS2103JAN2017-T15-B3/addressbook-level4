package seedu.address.logic.commands;

/**
 * Lists all/ the specified task type in the task manager to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists the details of the specified task "
            + "by the type of task or by date. "
            + "Parameters: [[floating/ deadline/ event]/ DATE]"
            + "Example: " + COMMAND_WORD + " floating";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";
    public static final String MESSAGE_NOT_LISTED = "Invalid input" + MESSAGE_USAGE;
    
    private static final String EMPTY_STRING = "";

    private String taskType;
    
    public ListCommand(String taskType) {
    	this.taskType = taskType;
    }

    @Override
    public CommandResult execute() {
    	if (taskType.equals(EMPTY_STRING)) {
    		model.updateFilteredListToShowAll();
    		return new CommandResult(MESSAGE_SUCCESS);
    	}
    	model.updateFilteredTaskListByTaskType(taskType);
    	return new CommandResult(MESSAGE_SUCCESS + " (" + taskType + ")");
    }
}

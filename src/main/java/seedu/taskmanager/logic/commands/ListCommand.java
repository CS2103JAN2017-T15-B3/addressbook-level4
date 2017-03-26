package seedu.taskmanager.logic.commands;

/**
 * Lists all/ the specified task type in the task manager to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists the details of the specified task "
            + "by the type of task or by date. \n" + "Parameters: [ [floating/ deadline/ event] / DATE] \n"
            + "Example: " + COMMAND_WORD + " floating";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";
    public static final String MESSAGE_NOT_LISTED = "Invalid input" + MESSAGE_USAGE;

    private static final String EMPTY_STRING = "";

    private String taskType = null;
    private String[] taskTypeAndDate;

    public ListCommand(String taskType) {
        this.taskType = taskType;
    }

    public ListCommand(String[] taskTypeAndDate) {
    	this.taskTypeAndDate = taskTypeAndDate;
    }

    @Override
    public CommandResult execute() {
        assert model != null;

        if (taskType != null) {
        	if (taskType.equals(EMPTY_STRING)) {
                model.updateFilteredListToShowAll();
                return new CommandResult(MESSAGE_SUCCESS);
            }
            model.updateFilteredTaskListByTaskTypeOrDate(taskType);
            return new CommandResult(MESSAGE_SUCCESS + " (" + taskType + ")");
        }

        model.updateFilteredTaskListByTaskTypeAndDate(taskTypeAndDate);
        return new CommandResult(MESSAGE_SUCCESS + " (" + taskTypeAndDate[0] + ", " +
        							taskTypeAndDate[1] + ")");
    }
}


package seedu.taskmanager.logic.commands;

import static org.junit.Assert.assertNotNull;

import static seedu.taskmanager.commons.util.CommonStringUtil.EMPTY_STRING;

// @@author A0140538J
/**
 * Lists all, task type, date or done status in the task manager to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists the details of all tasks, "
            + "task type, date or done status. \n" + "Parameters: [(floating/deadline/event) / DATE / (done/undone)] \n"
            + "Example: " + COMMAND_WORD + " floating";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";
    public static final String MESSAGE_NOT_LISTED = "Invalid input" + MESSAGE_USAGE;
    public static final String MESSAGE_FLOATING_NO_DATE = "A floating task has no dates";

    private String filter = null;
    private String[] filters;

    public ListCommand(String filter) {
        this.filter = filter;
    }

    public ListCommand(String[] filters) {
        this.filters = filters;
    }

    @Override
    public CommandResult execute() {
        assertNotNull(model);

        if (filter != null) {
            if (filter.equals(EMPTY_STRING)) {
                model.updateFilteredListToShowAll();
                return new CommandResult(MESSAGE_SUCCESS);
            }
            model.updateFilteredTaskListByOneFilter(filter);
            return new CommandResult(MESSAGE_SUCCESS + " (" + filter + ")");
        }

        model.updateFilteredTaskListByTaskTypeAndDate(filters);
        return new CommandResult(MESSAGE_SUCCESS + " (" + filters[0] + ", " + filters[1] + ")");
    }

    @Override
    public boolean mutatesTaskManager() {
        return false;
    }
}

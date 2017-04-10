
package seedu.taskmanager.logic.commands;

import static org.junit.Assert.assertNotNull;

import static seedu.taskmanager.commons.util.CommonStringUtil.DONE_STRING;
import static seedu.taskmanager.commons.util.CommonStringUtil.UNDONE_STRING;

import seedu.taskmanager.commons.core.Messages;
import seedu.taskmanager.commons.core.UnmodifiableObservableList;
import seedu.taskmanager.logic.commands.exceptions.CommandException;
import seedu.taskmanager.model.task.ReadOnlyTask;
import seedu.taskmanager.model.task.Task;
import seedu.taskmanager.model.task.UniqueTaskList;

// @@author A0140538J
/**
 * Marks a task done or undone.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task done/undone identified by the index number used in the last task listing.\n"
            + "Parameters: INDEX (must be a positive integer)\n" + "Example: " + COMMAND_WORD + " 1 done";

    public static final String MESSAGE_SUCCESS = "Marked Task: %1$s";
    public static final String MESSAGE_ALREADY_DONE = "This task is already marked as done.";
    public static final String MESSAGE_ALREADY_UNDONE = "This task is already marked as undone.";

    // Used for unexpected exceptions that needs re-implementation
    public static final String MESSAGE_GENERIC_ERROR = "MarkCommand Error";

    public final int targetIndex;
    public final String newMarkStatus;

    public MarkCommand(int targetIndex, String newMarkStatus) {
        assert targetIndex > 0;
        assertNotNull(newMarkStatus);

        this.targetIndex = targetIndex - 1;
        this.newMarkStatus = newMarkStatus;
    }

    @Override
    public CommandResult execute() throws CommandException {

        UnmodifiableObservableList<ReadOnlyTask> lastShownList = model.getFilteredTaskList();

        if (lastShownList.size() < targetIndex + 1) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        ReadOnlyTask toBeMarked = lastShownList.get(targetIndex);

        if (toBeMarked.isDone() && newMarkStatus.equals(DONE_STRING)) {
            throw new CommandException(MESSAGE_ALREADY_DONE);
        }

        if (!toBeMarked.isDone() && newMarkStatus.equals(UNDONE_STRING)) {
            throw new CommandException(MESSAGE_ALREADY_UNDONE);
        }

        try {
            Task markedTask = createMarkedTask(toBeMarked);
            model.updateTask(targetIndex, markedTask);
        } catch (UniqueTaskList.DuplicateTaskException dpe) {
            throw new CommandException(MESSAGE_GENERIC_ERROR);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, toBeMarked));
    }

    private Task createMarkedTask(ReadOnlyTask toBeMarked) {
        Task newMarkedTask = new Task(toBeMarked);

        if (newMarkedTask.isDone()) {
            newMarkedTask.setDoneStatus(false);
        } else {
            newMarkedTask.setDoneStatus(true);
        }

        return newMarkedTask;
    }

    @Override
    public boolean mutatesTaskManager() {
        return true;
    }

}

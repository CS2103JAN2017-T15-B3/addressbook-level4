package seedu.taskmanager.logic.commands;

import static seedu.taskmanager.logic.parser.AddCommandParser.NO_END_DATE;
import static seedu.taskmanager.logic.parser.AddCommandParser.NO_START_DATE;

import java.util.HashSet;
import java.util.Set;

import seedu.taskmanager.commons.exceptions.IllegalValueException;
import seedu.taskmanager.logic.commands.exceptions.CommandException;
import seedu.taskmanager.logic.parser.DateTimeUtil;
import seedu.taskmanager.model.tag.Tag;
import seedu.taskmanager.model.tag.UniqueTagList;
import seedu.taskmanager.model.task.DummyEndTaskDate;
import seedu.taskmanager.model.task.DummyStartTaskDate;
import seedu.taskmanager.model.task.Name;
import seedu.taskmanager.model.task.Task;
import seedu.taskmanager.model.task.TaskDate;
import seedu.taskmanager.model.task.UniqueTaskList;

/**
 * Adds a task to the task manager.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to PotaTodo. "
            + "Parameters: NAME [s/START_DATE_TIME] [e/END_DATE_TIME] [t/TAG]...\n" + "Example: " + COMMAND_WORD
            + " Meeting s/ 1 May 2017 6pm e/ 1 May 2017 7pm t/important";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task manager";

    private final Task toAdd;

    /**
     * Creates an AddCommand using raw values.
     *
     * @throws IllegalValueException
     *             if any of the raw values are invalid
     */
    public AddCommand(String name, String startDateString, String endDateString, Set<String> tags)
            throws IllegalValueException {

        final Set<Tag> tagSet = new HashSet<>();
        TaskDate startDate;
        TaskDate endDate;

        if (startDateString == NO_START_DATE) {

            startDate = new DummyStartTaskDate();

        } else {
            startDate = new TaskDate(DateTimeUtil.parseStartDateTime(startDateString));
        }

        if (endDateString == NO_END_DATE) {
            endDate = new DummyEndTaskDate();

        } else {
            endDate = new TaskDate(DateTimeUtil.parseEndDateTime(endDateString));
        }

        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Task(new Name(name), startDate, endDate, new UniqueTagList(tagSet));
    }

    @Override
    public CommandResult execute() throws CommandException {
        assert model != null;
        try {
            model.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniqueTaskList.DuplicateTaskException e) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

    }

}

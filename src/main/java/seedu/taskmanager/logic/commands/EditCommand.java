package seedu.taskmanager.logic.commands;

import static seedu.taskmanager.logic.parser.EditCommandParser.EMPTY_STRING;

import java.util.List;
import java.util.Optional;

import seedu.taskmanager.commons.core.Messages;
import seedu.taskmanager.commons.exceptions.IllegalValueException;
import seedu.taskmanager.logic.commands.exceptions.CommandException;
import seedu.taskmanager.model.tag.UniqueTagList;
import seedu.taskmanager.model.task.Name;
import seedu.taskmanager.model.task.ReadOnlyTask;
import seedu.taskmanager.model.task.Task;
import seedu.taskmanager.model.task.TaskDate;
import seedu.taskmanager.model.task.UniqueTaskList;

/**
 * Edits the details of an existing task in the task manager.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number as shown in the list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) [NAME] [s/START_DATE] [e/END_DATE] [t/TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task manager.";
    public static final String MESSAGE_START_AFTER_END = "The start date provided is after end date.";
    public static final String MESSAGE_INVALID_EDITTED_TASK = "The editted task is not in the right format."
            + " Please check the type of your task to edit accordingly.";

    private final int filteredTaskListIndex;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * @param filteredTaskListIndex
     *            the index of the task in the filtered task list to edit
     * @param editTaskDescriptor
     *            details to edit the task with
     */
    public EditCommand(int filteredTaskListIndex, EditTaskDescriptor editTaskDescriptor) {
        assert filteredTaskListIndex > 0;
        assert editTaskDescriptor != null;

        // converts filteredTaskListIndex from one-based to zero-based.
        this.filteredTaskListIndex = filteredTaskListIndex - 1;

        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute() throws CommandException {
        List<ReadOnlyTask> lastShownList = model.getFilteredTaskList();

        if (filteredTaskListIndex >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        ReadOnlyTask taskToEdit = lastShownList.get(filteredTaskListIndex);

        try {
            Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);
            model.updateTask(filteredTaskListIndex, editedTask);
        } catch (UniqueTaskList.DuplicateTaskException dpe) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);

        } catch (IllegalValueException ive) {
            throw new CommandException(ive.getMessage());

        }
        model.updateFilteredListToShowAll();

        return new CommandResult(String.format(MESSAGE_SUCCESS, taskToEdit));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(ReadOnlyTask taskToEdit, EditTaskDescriptor editTaskDescriptor)
            throws IllegalValueException {
        assert taskToEdit != null;

        Name updatedName = editTaskDescriptor.getName().orElseGet(taskToEdit::getName);

        UniqueTagList updatedTags = editTaskDescriptor.getTags().orElseGet(taskToEdit::getTags);

        TaskDate updatedStartDate = null;
        TaskDate updatedEndDate = null;

        if (!editTaskDescriptor.startDateRemoved()) {
            updatedStartDate = editTaskDescriptor.getStartDate().orElseGet(taskToEdit::getStartDate);
        }

        if (!editTaskDescriptor.endDateRemoved()) {
            updatedEndDate = editTaskDescriptor.getEndDate().orElseGet(taskToEdit::getEndDate);
        }

        Task createdTask = new Task(updatedName, updatedStartDate, updatedEndDate, updatedTags);

        // If the created task is an event, the startDate should be before the
        // endDate
        if (createdTask.isEvent()) {
            if (!createdTask.getStartDate().getTaskDate().before(createdTask.getEndDate().getTaskDate())) {
                throw new IllegalValueException(MESSAGE_START_AFTER_END);
            }
        }

        // Checks if a task is in a valid time structure.
        if (!createdTask.isValidTask()) {
            throw new IllegalValueException(MESSAGE_INVALID_EDITTED_TASK);

        }

        return createdTask;

    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will
     * replace the corresponding field value of the task.
     */
    public static class EditTaskDescriptor {

        private Optional<Name> name = Optional.empty();
        private Optional<TaskDate> startDate = Optional.empty();
        private Optional<TaskDate> endDate = Optional.empty();
        private Optional<UniqueTagList> tags = Optional.empty();

        // Needs to be static such that the flags carries over from
        // EditCommandParser
        private static boolean editFlag, startDateRemovedFlag, endDateRemovedFlag;

        public EditTaskDescriptor() {
            startDateRemovedFlag = false;
            endDateRemovedFlag = false;
            editFlag = false;
        }

        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            this.name = toCopy.getName();
            this.startDate = toCopy.getStartDate();
            this.endDate = toCopy.getEndDate();
            this.tags = toCopy.getTags();
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return editFlag;
        }

        public void setName(Optional<Name> name) {
            assert name != null;
            this.name = name;
            setEditted();
        }

        public Optional<Name> getName() {
            return name;
        }

        public void setStartDate(Optional<TaskDate> taskDate) {
            assert taskDate != null;
            this.startDate = taskDate;
            setEditted();
        }

        public Optional<TaskDate> getStartDate() {
            return startDate;
        }

        public boolean startDateRemoved() {
            return startDateRemovedFlag;
        }

        public void setStartDateRemovedFlag() {
            startDateRemovedFlag = true;
            setEditted();
        }

        public void setEndDate(Optional<TaskDate> taskDate) {
            assert taskDate != null;
            this.endDate = taskDate;
            setEditted();
        }

        public Optional<TaskDate> getEndDate() {
            return endDate;
        }

        public boolean endDateRemoved() {
            return endDateRemovedFlag;
        }

        public void setEndDateRemovedFlag() {
            endDateRemovedFlag = true;
            setEditted();
        }

        public void setTags(Optional<UniqueTagList> tags) {
            assert tags != null;
            this.tags = tags;
            setEditted();
        }

        public Optional<UniqueTagList> getTags() {
            return tags;
        }

        /**
         * Set the descriptor as editted even if no values are touched. Used in
         * optional taskdate implementation
         */
        public void setEditted() {
            editFlag = true;
        }
    }
}


package seedu.taskmanager.model;

import java.util.Set;

import seedu.taskmanager.commons.core.UnmodifiableObservableList;
import seedu.taskmanager.model.task.ReadOnlyTask;
import seedu.taskmanager.model.task.Task;
import seedu.taskmanager.model.task.UniqueTaskList;
import seedu.taskmanager.model.task.UniqueTaskList.DuplicateTaskException;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * Clears existing backing model and replaces with the provided new data.
     */
    void resetData(ReadOnlyTaskManager newData);

    /** Returns the TaskManager */
    ReadOnlyTaskManager getTaskManager();

    void changeFilePath(String newPath);

    /** Deletes the given task. */
    void deleteTask(ReadOnlyTask target) throws UniqueTaskList.TaskNotFoundException;

    /** Adds the given task */
    void addTask(Task task) throws UniqueTaskList.DuplicateTaskException;

    /**
     * Updates the task located at {@code filteredTaskListIndex} with {@code editedTask}.
     * @throws DuplicateTaskException
     *             if updating the task's details causes the task to be equivalent to another existing task in the list.
     * @throws IndexOutOfBoundsException
     *             if {@code filteredTaskListIndex} < 0 or >= the size of the filtered list.
     */
    void updateTask(int filteredTaskListIndex, ReadOnlyTask editedTask) throws UniqueTaskList.DuplicateTaskException;

    /**
     * Returns the filtered task list as an {@code UnmodifiableObservableList<ReadOnlyTask>}
     */
    UnmodifiableObservableList<ReadOnlyTask> getFilteredTaskList();

    /** Updates the filter of the filtered task list to show all tasks */
    void updateFilteredListToShowAll();

    /**
     * Updates the filter of the filtered task list to filter by the given keywords
     */
    void updateFilteredTaskListByTaskName(Set<String> keywords);

    /**
     * Updates the filter of the filtered task list to filter by the given task type or date
     */
    void updateFilteredTaskListByOneFilter(String taskType);

    /**
     * Updates the filter of the filtered task list to filter by the given task type and date
     */
    void updateFilteredTaskListByTaskTypeAndDate(String[] taskTypeAndDate);

    // @@author A0140417R
    void loadPreviousState() throws ArrayIndexOutOfBoundsException;

    void loadNextState() throws ArrayIndexOutOfBoundsException;

    void saveState(String commandString);
    // @@author

    /**
     * Sets a visual notification for tasks expiring within the stipulated time.
     */
    void setNotification(String duration);
}

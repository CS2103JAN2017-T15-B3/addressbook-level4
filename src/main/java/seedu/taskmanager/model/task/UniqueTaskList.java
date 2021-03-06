
package seedu.taskmanager.model.task;

import static org.junit.Assert.assertNotNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.taskmanager.commons.core.UnmodifiableObservableList;
import seedu.taskmanager.commons.exceptions.DuplicateDataException;
import seedu.taskmanager.commons.util.CollectionUtil;

/**
 * A list of tasks that enforces uniqueness between its elements and does not allow nulls. Supports a minimal set of
 * list operations.
 * @see Task#equals(Object)
 * @see CollectionUtil#elementsAreUnique(Collection)
 */
public class UniqueTaskList implements Iterable<Task> {

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(ReadOnlyTask toCheck) {
        assertNotNull(toCheck);
        return internalList.contains(toCheck);
    }

    /**
     * Adds a task to the list.
     * @throws DuplicateTaskException
     *         if the task to add is a duplicate of an existing task in the list.
     */
    public void add(Task toAdd) throws DuplicateTaskException {
        assertNotNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        internalList.add(toAdd);
    }

    /**
     * Updates the task in the list at position {@code index} with {@code editedTask}.
     * @throws DuplicateTaskException
     *         if updating the task's details causes the task to be equivalent to another existing task in the list.
     * @throws IndexOutOfBoundsException
     *         if {@code index} < 0 or >= the size of the list.
     */
    public void updateTask(int index, ReadOnlyTask editedTask) throws DuplicateTaskException {
        assertNotNull(editedTask);

        Task taskToUpdate = internalList.get(index);
        if (taskToUpdate.isDone() == editedTask.isDone() && !taskToUpdate.equals(editedTask)
                && internalList.contains(editedTask)) {
            throw new DuplicateTaskException();
        }

        taskToUpdate.resetData(editedTask);
        // TODO: The code below is just a workaround to notify observers of the
        // updated task.
        // The right way is to implement observable properties in the Task
        // class.
        // Then, TaskCard should then bind its text labels to those observable
        // properties.
        // internalList.remove(index);
        // internalList.add(0, taskToUpdate);
        internalList.set(index, taskToUpdate);
    }

    /**
     * Removes the equivalent task from the list.
     * @throws TaskNotFoundException
     *         if no such task could be found in the list.
     */
    public boolean remove(ReadOnlyTask toRemove) throws TaskNotFoundException {
        assertNotNull(toRemove);
        final boolean taskFoundAndDeleted = internalList.remove(toRemove);
        if (!taskFoundAndDeleted) {
            throw new TaskNotFoundException();
        }
        return taskFoundAndDeleted;
    }

    public void setTasks(UniqueTaskList replacement) {
        this.internalList.setAll(replacement.internalList);
    }

    public void setTasks(List<? extends ReadOnlyTask> tasks) throws DuplicateTaskException {
        final UniqueTaskList replacement = new UniqueTaskList();
        for (final ReadOnlyTask task : tasks) {
            replacement.add(new Task(task));
        }
        setTasks(replacement);
    }

    public UnmodifiableObservableList<Task> asObservableList() {
        return new UnmodifiableObservableList<>(internalList);
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTaskList // instanceof handles nulls
                        && this.internalList.equals(((UniqueTaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicateTaskException extends DuplicateDataException {
        /**
         *
         */
        private static final long serialVersionUID = -6262186798759315300L;

        protected DuplicateTaskException() {
            super("Operation would result in duplicate tasks");
        }
    }

    /**
     * Signals that an operation targeting a specified task in the list would fail because there is no such matching
     * task in the list.
     */
    public static class TaskNotFoundException extends Exception {
        private static final long serialVersionUID = 3500678284364510933L;
    }

}

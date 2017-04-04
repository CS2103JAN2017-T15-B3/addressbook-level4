
package seedu.taskmanager.model;

import java.util.Set;
import java.util.logging.Logger;

import javafx.collections.transformation.FilteredList;
import seedu.taskmanager.commons.core.ComponentManager;
import seedu.taskmanager.commons.core.LogsCenter;
import seedu.taskmanager.commons.core.UnmodifiableObservableList;
import seedu.taskmanager.commons.events.model.FilePathChangedEvent;
import seedu.taskmanager.commons.events.model.TaskManagerChangedEvent;
import seedu.taskmanager.commons.exceptions.IllegalValueException;
import seedu.taskmanager.commons.util.CollectionUtil;
import seedu.taskmanager.commons.util.StringUtil;
import seedu.taskmanager.logic.parser.DateTimeUtil;
import seedu.taskmanager.model.task.ReadOnlyTask;
import seedu.taskmanager.model.task.Task;
import seedu.taskmanager.model.task.TaskDate;
import seedu.taskmanager.model.task.UniqueTaskList;
import seedu.taskmanager.model.task.UniqueTaskList.TaskNotFoundException;

/**
 * Represents the in-memory model of the task manager data. All changes to any model should be synchronized.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private static final String STRING_INITIAL = "Initial";

    private final TaskManager taskManager;
    private final TaskNotifier taskNotifier;
    private final FilteredList<ReadOnlyTask> filteredTasks;
    private final TaskManagerStateManager stateManager;

    /**
     * Initializes a ModelManager with the given taskManager and userPrefs.
     */
    public ModelManager(ReadOnlyTaskManager taskManager, UserPrefs userPrefs) {
        super();
        assert !CollectionUtil.isAnyNull(taskManager, userPrefs);

        logger.fine("Initializing with task manager: " + taskManager + " and user prefs " + userPrefs);

        // @@author A0140538J
        this.taskNotifier = new TaskNotifierManager(userPrefs);
        // @@author
        this.taskManager = new TaskManager(taskManager);
        filteredTasks = new FilteredList<>(this.taskManager.getTaskList());
        TaskManagerState initState = new TaskManagerState(taskManager, STRING_INITIAL);
        // @@author A0140417R
        this.stateManager = new TaskManagerStateManager(initState);
        // @@author
    }

    public ModelManager() {
        this(new TaskManager(), new UserPrefs());
    }

    // @@author A0140538J
    @Override
    public void setNotification(String duration) {
        taskNotifier.setNotification(duration);
    }

    // @@author A0140417R
    @Override
    public void saveState(String commandString) {
        stateManager.addState(new TaskManagerState(taskManager, commandString));
    }
    // @@author

    @Override
    public void resetData(ReadOnlyTaskManager newData) {
        taskManager.resetData(newData);
        indicateTaskManagerChanged();
    }

    @Override
    public ReadOnlyTaskManager getTaskManager() {
        return taskManager;
    }

    // @@author A0140417R
    @Override
    public void changeFilePath(String newPath) {
        raise(new FilePathChangedEvent(newPath));
    }
    // @@author

    /** Raises an event to indicate the model has changed */
    private void indicateTaskManagerChanged() {
        raise(new TaskManagerChangedEvent(taskManager));
    }

    @Override
    public synchronized void deleteTask(ReadOnlyTask target) throws TaskNotFoundException {
        taskManager.removeTask(target);
        indicateTaskManagerChanged();
    }

    @Override
    public synchronized void addTask(Task task) throws UniqueTaskList.DuplicateTaskException {
        taskManager.addTask(task);
        updateFilteredListToShowAll();
        indicateTaskManagerChanged();
    }

    @Override
    public void updateTask(int filteredTaskListIndex, ReadOnlyTask editedTask)
            throws UniqueTaskList.DuplicateTaskException {
        assert editedTask != null;

        int taskManagerIndex = filteredTasks.getSourceIndex(filteredTaskListIndex);
        taskManager.updateTask(taskManagerIndex, editedTask);
        indicateTaskManagerChanged();
    }

    // @@author A0140417R
    @Override
    public void loadPreviousState() throws IndexOutOfBoundsException {
        taskManager.resetData(stateManager.getPreviousState().getTaskManager());
        indicateTaskManagerChanged();
    }

    @Override
    public void loadNextState() throws IndexOutOfBoundsException {
        taskManager.resetData(stateManager.getNextState().getTaskManager());
        indicateTaskManagerChanged();
    }
    // @@author

    // =========== Filtered Task List Accessors
    // =============================================================

    @Override
    public UnmodifiableObservableList<ReadOnlyTask> getFilteredTaskList() {
        return new UnmodifiableObservableList<>(filteredTasks);
    }

    @Override
    public void updateFilteredListToShowAll() {
        filteredTasks.setPredicate(null);
    }

    @Override
    public void updateFilteredTaskListByTaskName(Set<String> keywords) {
        updateFilteredTaskList(new PredicateExpression(new NameQualifier(keywords)));
    }

    // @@author A0140538J
    @Override
    public void updateFilteredTaskListByOneFilter(String filter) {
        updateFilteredTaskList(new PredicateExpression(new OneFilterQualifier(filter)));
    }

    @Override
    public void updateFilteredTaskListByTaskTypeAndDate(String[] taskTypeAndDate) {
        updateFilteredTaskList(new PredicateExpression(new TypeAndDateQualifier(taskTypeAndDate)));
    }
    // @@author

    private void updateFilteredTaskList(Expression expression) {
        filteredTasks.setPredicate(expression::satisfies);
    }

    // ========== Inner classes/interfaces used for filtering
    // =================================================

    interface Expression {
        boolean satisfies(ReadOnlyTask task);

        @Override
        String toString();
    }

    private class PredicateExpression implements Expression {

        private final Qualifier qualifier;

        PredicateExpression(Qualifier qualifier) {
            this.qualifier = qualifier;
        }

        @Override
        public boolean satisfies(ReadOnlyTask task) {
            return qualifier.run(task);
        }

        @Override
        public String toString() {
            return qualifier.toString();
        }
    }

    interface Qualifier {
        boolean run(ReadOnlyTask task);

        @Override
        String toString();
    }

    private class NameQualifier implements Qualifier {
        private Set<String> nameKeyWords;

        NameQualifier(Set<String> nameKeyWords) {
            this.nameKeyWords = nameKeyWords;
        }

        @Override
        public boolean run(ReadOnlyTask task) {
            return nameKeyWords.stream()
                    .filter(keyword -> StringUtil.containsWordIgnoreCase(task.getName().fullName, keyword)).findAny()
                    .isPresent();
        }

        @Override
        public String toString() {
            return "name=" + String.join(", ", nameKeyWords);
        }
    }

    // @@author A0140538J
    private class OneFilterQualifier implements Qualifier {
        private String filter;

        OneFilterQualifier(String filter) {
            this.filter = filter;
        }

        @Override
        public boolean run(ReadOnlyTask task) {
            switch (filter) {
            case "floating":
                return task.isFloating();
            case "deadline":
                return task.isDeadline();
            case "event":
                return task.isEvent();
            case "done":
                return task.isDone();
            case "undone":
                return !task.isDone();
            // for parsing date
            default:
                try {
                    TaskDate date = DateTimeUtil.parseDateTime(filter);
                    return (task.getStartDate() != null && task.getStartDate().getOnlyDate().equals(date.getOnlyDate()))
                            || (task.getEndDate() != null
                                    && task.getEndDate().getOnlyDate().equals(date.getOnlyDate()));

                } catch (IllegalValueException ive) {
                    // Deliberately empty as filter will not throw exception
                    return false;
                }
            }
        }
    }

    private class TypeAndDateQualifier implements Qualifier {
        private String taskType;
        private TaskDate date;

        TypeAndDateQualifier(String[] taskTypeAndDate) {
            taskType = taskTypeAndDate[0];

            try {
                date = DateTimeUtil.parseDateTime(taskTypeAndDate[1]);
            } catch (IllegalValueException ive) {
                // Deliberately empty as this date will not throw exception
            }
        }

        @Override
        public boolean run(ReadOnlyTask task) {

            boolean dateFilter = (task.getStartDate() != null
                    && task.getStartDate().getOnlyDate().equals(date.getOnlyDate()))
                    || (task.getEndDate() != null && task.getEndDate().getOnlyDate().equals(date.getOnlyDate()));

            switch (taskType) {
            case "floating":
                return task.isFloating() && dateFilter;
            case "deadline":
                return task.isDeadline() && dateFilter;
            case "event":
                return task.isEvent() && dateFilter;
            default:
                // will never reach this step
                return false;
            }
        }
    }
}

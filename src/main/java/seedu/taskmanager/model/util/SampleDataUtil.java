package seedu.taskmanager.model.util;

import java.util.Optional;

import seedu.taskmanager.commons.exceptions.IllegalValueException;
import seedu.taskmanager.logic.parser.DateTimeUtil;
import seedu.taskmanager.model.ReadOnlyTaskManager;
import seedu.taskmanager.model.TaskManager;
import seedu.taskmanager.model.tag.UniqueTagList;
import seedu.taskmanager.model.task.Name;
import seedu.taskmanager.model.task.Task;
import seedu.taskmanager.model.task.TaskDate;
import seedu.taskmanager.model.task.UniqueTaskList.DuplicateTaskException;

public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        try {
            return new Task[] { new Task(new Name("Meeting with boss"), Optional.empty(), Optional.of(new TaskDate(DateTimeUtil.parseEndDateTime("tmr"))), new UniqueTagList("impt")),
            		new Task(new Name("Rest for the day"), Optional.empty(), Optional.empty(), new UniqueTagList("VERY IMPT")),
            		new Task(new Name("Birthday party"), Optional.of(new TaskDate(DateTimeUtil.parseStartDateTime("14 june 2017 10pm"))), Optional.of(new TaskDate(DateTimeUtil.parseEndDateTime("14 june 2017 6pm"))), new UniqueTagList("impt")) };
        } catch (IllegalValueException e) {
            throw new AssertionError("sample data cannot be invalid", e);
        }
    }

    public static ReadOnlyTaskManager getSampleTaskManager() {
        try {
            TaskManager sampleAB = new TaskManager();
            for (Task sampleTask : getSampleTasks()) {
                sampleAB.addTask(sampleTask);
            }
            return sampleAB;
        } catch (DuplicateTaskException e) {
            throw new AssertionError("sample data cannot contain duplicate tasks", e);
        }
    }
}

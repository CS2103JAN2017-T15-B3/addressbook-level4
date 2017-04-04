
package seedu.taskmanager.model.util;

import seedu.taskmanager.commons.exceptions.IllegalValueException;
import seedu.taskmanager.logic.parser.DateTimeUtil;
import seedu.taskmanager.model.ReadOnlyTaskManager;
import seedu.taskmanager.model.TaskManager;
import seedu.taskmanager.model.tag.UniqueTagList;
import seedu.taskmanager.model.task.Name;
import seedu.taskmanager.model.task.Task;
import seedu.taskmanager.model.task.UniqueTaskList.DuplicateTaskException;

//@@author A0130277L

public class SampleDataUtil {

    // Sample events
    private static final String EVENT1_NAME = "Meeting with prof";
    private static final String EVENT2_NAME = "Dinner with friend";

    private static final String EVENT1_START_DATE_STRING = "26 March 2017, 10:00 PM";
    private static final String EVENT1_END_DATE_STRING = "26 March 2017, 11:00 PM";
    private static final String EVENT2_START_DATE_STRING = "2 May 2018, 8:00 AM";
    private static final String EVENT2_END_DATE_STRING = "2 May 2018, 10:00 AM";

    // Sample deadlines
    private static final String DDL1_NAME = "Project submission";
    private static final String DDL2_NAME = "Pay bills";

    private static final String DDL1_DUE_TIME_STRING = "25 March 2017, 11:00 PM";
    private static final String DDL2_DUE_TIME_STRING = "26 May 2017";

    // Sample floating tasks
    public static final String FLT1_NAME = "Stay healthy";
    public static final String FLT2_NAME = "Smile more";

    public static Task[] getSampleTasks() {
        try {
            return new Task[] {
                    new Task(new Name(EVENT1_NAME), DateTimeUtil.parseStartDateTime(EVENT1_START_DATE_STRING),
                            DateTimeUtil.parseEndDateTime(EVENT1_END_DATE_STRING), new UniqueTagList("important")),
                    new Task(new Name(DDL1_NAME), null, DateTimeUtil.parseEndDateTime(DDL1_DUE_TIME_STRING),
                            new UniqueTagList()),
                    new Task(new Name(FLT1_NAME), new UniqueTagList()),
                    new Task(new Name(EVENT2_NAME), DateTimeUtil.parseStartDateTime(EVENT2_START_DATE_STRING),
                            DateTimeUtil.parseEndDateTime(EVENT2_END_DATE_STRING), new UniqueTagList()),
                    new Task(new Name(DDL2_NAME), null, DateTimeUtil.parseEndDateTime(DDL2_DUE_TIME_STRING),
                            new UniqueTagList("urgent")),
                    new Task(new Name(FLT2_NAME), new UniqueTagList()) };
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

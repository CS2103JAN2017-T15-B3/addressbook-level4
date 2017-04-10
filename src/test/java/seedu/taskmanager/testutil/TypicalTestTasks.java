
package seedu.taskmanager.testutil;

import seedu.taskmanager.commons.exceptions.IllegalValueException;
import seedu.taskmanager.logic.parser.DateTimeUtil;
import seedu.taskmanager.model.TaskManager;
import seedu.taskmanager.model.task.Task;
import seedu.taskmanager.model.task.UniqueTaskList;

//@@author A0130277L
/**
 * Stores typical testing tasks
 */
public class TypicalTestTasks {

    public TestTask event1, event2, event3, ddl1, ddl2, ddl3, flt1, flt2, flt3, eventConflicting;

    // Sample events
    public static final String EVENT1_NAME = "Meeting with principal";
    public static final String EVENT2_NAME = "Meeting with vice-principal";
    public static final String EVENT3_NAME = "Golf with president";

    public static final String EVENT1_START_DATE_STRING = "26 March 2017, 10:00 PM";
    public static final String EVENT1_END_DATE_STRING = "26 March 2017, 11:00 PM";
    public static final String EVENT2_START_DATE_STRING = "2 May 2018, 8:00 AM";
    public static final String EVENT2_END_DATE_STRING = "2 May 2018, 10:00 AM";
    public static final String EVENT3_START_DATE_STRING = "20 Aug 2017, 8:00 PM";
    public static final String EVENT3_END_DATE_STRING = "20 Aug 2017, 11:00 PM";

    // Sample deadlines
    public static final String DDL1_NAME = "Save uncle Ben";
    public static final String DDL2_NAME = "Defeat Joker";
    public static final String DDL3_NAME = "Finish building time machine";

    public static final String DDL1_DUE_TIME_STRING = "25 March 2017, 11:00 PM";
    public static final String DDL2_DUE_TIME_STRING = "26 May 2017, 10:00 AM";
    public static final String DDL3_DUE_TIME_STRING = "28 July 2017, 9:30 PM";

    // Sample floating tasks
    public static final String FLT1_NAME = "Maintain six pack abs";
    public static final String FLT2_NAME = "Stay as the strongest human";
    public static final String FLT3_NAME = "Sleep before 11 everyday";

    // Sample conflicting event
    public static final String CONFLICT_EVENT_NAME = "Conflicting Event";

    public TypicalTestTasks() {
        try {
            event1 = new TaskBuilder().withName(EVENT1_NAME)
                    .withStartDate(DateTimeUtil.parseStartDateTime(EVENT1_START_DATE_STRING))
                    .withEndDate(DateTimeUtil.parseEndDateTime(EVENT1_END_DATE_STRING)).withTags("important").build();
            event2 = new TaskBuilder().withName(EVENT2_NAME)
                    .withStartDate(DateTimeUtil.parseStartDateTime(EVENT2_START_DATE_STRING))
                    .withEndDate(DateTimeUtil.parseEndDateTime(EVENT2_END_DATE_STRING)).build();

            ddl1 = new TaskBuilder().withName(DDL1_NAME)
                    .withEndDate(DateTimeUtil.parseEndDateTime(DDL1_DUE_TIME_STRING)).withTags("urgent")
                    .withTags("dying").build();
            ddl2 = new TaskBuilder().withName(DDL2_NAME)
                    .withEndDate(DateTimeUtil.parseEndDateTime(DDL2_DUE_TIME_STRING)).build();

            flt1 = new TaskBuilder().withName(FLT1_NAME).withTags("essential").build();
            flt2 = new TaskBuilder().withName(FLT2_NAME).withTags("easy").build();

            // Manually add only
            event3 = new TaskBuilder().withName(EVENT3_NAME)
                    .withStartDate(DateTimeUtil.parseStartDateTime(EVENT3_START_DATE_STRING))
                    .withEndDate(DateTimeUtil.parseEndDateTime(EVENT3_END_DATE_STRING)).build();
            ddl3 = new TaskBuilder().withName(DDL3_NAME)
                    .withEndDate(DateTimeUtil.parseEndDateTime(DDL3_DUE_TIME_STRING)).build();
            flt3 = new TaskBuilder().withName(FLT3_NAME).withTags("hard").withTags("daunting").build();

            // Event conflicting with event 1 for manual input
            eventConflicting = new TaskBuilder().withName(CONFLICT_EVENT_NAME)
                    .withStartDate(DateTimeUtil.parseStartDateTime(EVENT1_START_DATE_STRING))
                    .withEndDate(DateTimeUtil.parseEndDateTime(EVENT1_END_DATE_STRING)).build();

        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadTaskManagerWithSampleData(TaskManager ab) {
        for (TestTask task : new TypicalTestTasks().getTypicalTasks()) {
            try {
                ab.addTask(new Task(task));
            } catch (UniqueTaskList.DuplicateTaskException e) {
                assert false : "not possible";
            }
        }
    }

    public TestTask[] getTypicalTasks() {
        return new TestTask[] {
            event1,
            event2,
            ddl1,
            ddl2,
            flt1,
            flt2
        };
    }

    public TaskManager getTypicalTaskManager() {
        TaskManager ab = new TaskManager();
        loadTaskManagerWithSampleData(ab);
        return ab;
    }
}

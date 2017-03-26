package seedu.taskmanager.testutil;

import seedu.taskmanager.commons.exceptions.IllegalValueException;
import seedu.taskmanager.logic.parser.DateTimeUtil;
import seedu.taskmanager.model.TaskManager;
import seedu.taskmanager.model.task.Task;
import seedu.taskmanager.model.task.TaskDate;
import seedu.taskmanager.model.task.UniqueTaskList;

/**
 *
 */
public class TypicalTestTasks {

    public TestTask event1, event2, event3, ddl1, ddl2, ddl3, flt1, flt2, flt3;

    // Sample events
    public String EVENT1_NAME = "Meeting with principal";
    public String EVENT2_NAME = "Meeting with vice-principal";
    public String EVENT3_NAME = "Golf with president";

    public String EVENT1_START_DATE_STRING = "26 March 2017, 10:00 PM";
    public String EVENT1_END_DATE_STRING = "26 March 2017, 11:00 PM";
    public String EVENT2_START_DATE_STRING = "2 May 2018, 8:00 AM";
    public String EVENT2_END_DATE_STRING = "2 May 2018, 10:00 AM";
    public String EVENT3_START_DATE_STRING = "20 Aug 2017, 8:00 PM";
    public String EVENT3_END_DATE_STRING = "20 Aug 2017, 12:00 PM";

    // Sample deadlines
    public String DDL1_NAME = "Save uncle Ben";
    public String DDL2_NAME = "Defeat Joker";
    public String DDL3_NAME = "Finish building time machine";

    public String DDL1_DUE_TIME_STRING = "25 March 2017, 11:00 PM";
    public String DDL2_DUE_TIME_STRING = "26 May 2017, 10:00 AM";
    public String DDL3_DUE_TIME_STRING = "28 July 2017, 9:30 PM";

    // Sample floating tasks
    public String FLT1_NAME = "Maintain six pack abs";
    public String FLT2_NAME = "Stay as the strongest human";
    public String FLT3_NAME = "Sleep before 11 everyday";

    public TypicalTestTasks() {
        try {
            event1 = new TaskBuilder().withName(EVENT1_NAME)
                    .withStartDate(new TaskDate(DateTimeUtil.parseStartDateTime(EVENT1_START_DATE_STRING)))
                    .withEndDate(new TaskDate(DateTimeUtil.parseEndDateTime(EVENT1_END_DATE_STRING))).withTags("important")
                    .build();
            event2 = new TaskBuilder().withName(EVENT2_NAME)
                    .withStartDate(new TaskDate(DateTimeUtil.parseStartDateTime(EVENT2_START_DATE_STRING)))
                    .withEndDate(new TaskDate(DateTimeUtil.parseEndDateTime(EVENT2_END_DATE_STRING))).build();

            ddl1 = new TaskBuilder().withName(DDL1_NAME)
                    .withEndDate(new TaskDate(DateTimeUtil.parseEndDateTime(DDL1_DUE_TIME_STRING))).withTags("urgent")
                    .withTags("dying").build();
            ddl2 = new TaskBuilder().withName(DDL2_NAME)
                    .withEndDate(new TaskDate(DateTimeUtil.parseEndDateTime(DDL2_DUE_TIME_STRING))).build();

            flt1 = new TaskBuilder().withName(FLT1_NAME).withTags("essential").build();
            flt2 = new TaskBuilder().withName(FLT2_NAME).withTags("easy").build();

            // Manually add only
            event3 = new TaskBuilder().withName(EVENT3_NAME)
                    .withStartDate(new TaskDate(DateTimeUtil.parseStartDateTime(EVENT3_START_DATE_STRING)))
                    .withEndDate(new TaskDate(DateTimeUtil.parseEndDateTime(EVENT3_END_DATE_STRING))).build();
            ddl3 = new TaskBuilder().withName(DDL3_NAME)
                    .withEndDate(new TaskDate(DateTimeUtil.parseEndDateTime(DDL3_DUE_TIME_STRING))).build();
            flt3 = new TaskBuilder().withName(FLT3_NAME).withTags("hard").withTags("daunting").build();

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
        return new TestTask[] { event1, event2, ddl1, ddl2, flt1, flt2 };
    }

    public TaskManager getTypicalTaskManager() {
        TaskManager ab = new TaskManager();
        loadTaskManagerWithSampleData(ab);
        return ab;
    }
}

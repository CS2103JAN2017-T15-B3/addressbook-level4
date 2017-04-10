
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
    public static final String EVENT1_NAME = "Meeting with principal";
    public static final String EVENT2_NAME = "Meeting with vice principal";
    public static final String EVENT3_NAME = "Golf with president";
    public static final String EVENT4_NAME = "Golf with vice prisident";
    public static final String EVENT5_NAME = "Dinner with cowokers";
    public static final String EVENT6_NAME = "Dinner with boss";
    public static final String EVENT7_NAME = "Meeting with boss";
    public static final String EVENT8_NAME = "Meeting with president";
    public static final String EVENT9_NAME = "KTV with coworker";
    public static final String EVENT10_NAME = "KTV with boss";
    public static final String EVENT11_NAME = "KTV with manager";
    public static final String EVENT12_NAME = "Movie with coworker";
    public static final String EVENT13_NAME = "Movie with friends";
    public static final String EVENT14_NAME = "Movie with boss";
    public static final String EVENT15_NAME = "Dinner with friends";
    public static final String EVENT16_NAME = "Dinner with president";
    public static final String EVENT17_NAME = "Golf with boss";

    public static final String EVENT1_START_DATE_STRING = "1 April 2017, 9:00 PM";
    public static final String EVENT1_END_DATE_STRING = "1 April 2017, 10:00 PM";
    public static final String EVENT2_START_DATE_STRING = "2 April 2017, 9:00 PM";
    public static final String EVENT2_END_DATE_STRING = "2 April 2017, 10:00 PM";
    public static final String EVENT3_START_DATE_STRING = "3 April 2017, 9:00 PM";
    public static final String EVENT3_END_DATE_STRING = "3 April 2017, 10:00 PM";
    public static final String EVENT4_START_DATE_STRING = "4 April 2017, 9:00 PM";
    public static final String EVENT4_END_DATE_STRING = "4 April 2017, 10:00 PM";
    public static final String EVENT5_START_DATE_STRING = "5 April 2017, 9:00 PM";
    public static final String EVENT5_END_DATE_STRING = "5 April 2017, 10:00 PM";
    public static final String EVENT6_START_DATE_STRING = "6 April 2017, 9:00 PM";
    public static final String EVENT6_END_DATE_STRING = "6 April 2017, 10:00 PM";
    public static final String EVENT7_START_DATE_STRING = "7 April 2017, 9:00 PM";
    public static final String EVENT7_END_DATE_STRING = "7 April 2017, 10:00 PM";
    public static final String EVENT8_START_DATE_STRING = "8 April 2017, 9:00 PM";
    public static final String EVENT8_END_DATE_STRING = "8 April 2017, 10:00 PM";
    public static final String EVENT9_START_DATE_STRING = "9 April 2017, 9:00 PM";
    public static final String EVENT9_END_DATE_STRING = "9 April 2017, 10:00 PM";
    public static final String EVENT10_START_DATE_STRING = "10 April 2017, 9:00 PM";
    public static final String EVENT10_END_DATE_STRING = "10 April 2017, 10:00 PM";
    public static final String EVENT11_START_DATE_STRING = "11 April 2017, 9:00 PM";
    public static final String EVENT11_END_DATE_STRING = "11 April 2017, 10:00 PM";
    public static final String EVENT12_START_DATE_STRING = "12 April 2017, 9:00 PM";
    public static final String EVENT12_END_DATE_STRING = "12 April 2017, 10:00 PM";
    public static final String EVENT13_START_DATE_STRING = "13 April 2017, 9:00 PM";
    public static final String EVENT13_END_DATE_STRING = "13 April 2017, 10:00 PM";
    public static final String EVENT14_START_DATE_STRING = "14 April 2017, 9:00 PM";
    public static final String EVENT14_END_DATE_STRING = "14 April 2017, 10:00 PM";
    public static final String EVENT15_START_DATE_STRING = "15 April 2017, 9:00 PM";
    public static final String EVENT15_END_DATE_STRING = "15 April 2017, 10:00 PM";
    public static final String EVENT16_START_DATE_STRING = "16 April 2017, 9:00 PM";
    public static final String EVENT16_END_DATE_STRING = "16 April 2017, 10:00 PM";
    public static final String EVENT17_START_DATE_STRING = "17 April 2017, 9:00 PM";
    public static final String EVENT17_END_DATE_STRING = "17 April 2017, 10:00 PM";

    // Sample deadlines
    public static final String DDL1_NAME = "Confess to love";
    public static final String DDL2_NAME = "Finish project";
    public static final String DDL3_NAME = "Finish researches";
    public static final String DDL4_NAME = "Ask for career advice";
    public static final String DDL5_NAME = "Talk to HR";
    public static final String DDL6_NAME = "Consult with management";
    public static final String DDL7_NAME = "Consult with cowork";
    public static final String DDL8_NAME = "Learn Japanese";
    public static final String DDL9_NAME = "Learn Java";
    public static final String DDL10_NAME = "Finish presentation slides";
    public static final String DDL11_NAME = "Ask for workplace advice";
    public static final String DDL12_NAME = "Report to boss on project progress";
    public static final String DDL13_NAME = "Write test cases";
    public static final String DDL14_NAME = "Finish working on current project";
    public static final String DDL15_NAME = "Consult with HR";
    public static final String DDL16_NAME = "Consult with manager";
    public static final String DDL17_NAME = "Talk to coworker on project";

    public static final String DDL1_DUE_TIME_STRING = "25 April 2017, 11:00 PM";
    public static final String DDL2_DUE_TIME_STRING = "26 April 2017, 10:00 AM";
    public static final String DDL3_DUE_TIME_STRING = "2 April 2017, 9:30 PM";
    public static final String DDL4_DUE_TIME_STRING = "3 April 2017, 9:30 PM";
    public static final String DDL5_DUE_TIME_STRING = "1 April 2017, 9:30 PM";
    public static final String DDL6_DUE_TIME_STRING = "10 April 2017, 9:30 PM";
    public static final String DDL7_DUE_TIME_STRING = "11 April 2017, 9:30 PM";
    public static final String DDL8_DUE_TIME_STRING = "21 April 2017, 9:30 PM";
    public static final String DDL9_DUE_TIME_STRING = "30 April 2017, 9:30 PM";
    public static final String DDL10_DUE_TIME_STRING = "29 April 2017, 9:30 PM";
    public static final String DDL11_DUE_TIME_STRING = "24 April 2017, 9:30 PM";
    public static final String DDL12_DUE_TIME_STRING = "19 April 2017, 9:30 PM";
    public static final String DDL13_DUE_TIME_STRING = "8 April 2017, 9:30 PM";
    public static final String DDL14_DUE_TIME_STRING = "9 April 2017, 9:30 PM";
    public static final String DDL15_DUE_TIME_STRING = "14 April 2017, 9:30 PM";
    public static final String DDL16_DUE_TIME_STRING = "1 April 2017, 9:30 PM";
    public static final String DDL17_DUE_TIME_STRING = "15 April 2017, 9:30 PM";

    // Sample floating tasks
    public static final String FLT1_NAME = "Stay healthy";
    public static final String FLT2_NAME = "Stay confident";
    public static final String FLT3_NAME = "Sleep before 11 everyday";
    public static final String FLT4_NAME = "Stay motivated";
    public static final String FLT5_NAME = "Try hard in job";
    public static final String FLT6_NAME = "Keep learning";
    public static final String FLT7_NAME = "Keep asking questions";
    public static final String FLT8_NAME = "Stay fit";
    public static final String FLT9_NAME = "Keep reading books";
    public static final String FLT10_NAME = "Keep exercising";
    public static final String FLT11_NAME = "Stay awake during work";
    public static final String FLT12_NAME = "Stay positive";
    public static final String FLT13_NAME = "Keep jogging everyday";
    public static final String FLT14_NAME = "Keep writing diary";
    public static final String FLT15_NAME = "Smile everyday";
    public static final String FLT16_NAME = "Sing everyday";
    public static final String FLT17_NAME = "Talk to a stranger everyday";

    public static Task[] getSampleTasks() {

        try {
            return new Task[] {
                new Task(new Name(EVENT1_NAME), DateTimeUtil.parseStartDateTime(EVENT1_START_DATE_STRING),
                        DateTimeUtil.parseEndDateTime(EVENT1_END_DATE_STRING), new UniqueTagList("important"), true,
                        false),
                new Task(new Name(DDL1_NAME), null, DateTimeUtil.parseEndDateTime(DDL1_DUE_TIME_STRING),
                        new UniqueTagList(), false, false),
                new Task(new Name(FLT1_NAME), null, null, new UniqueTagList(), false, false),
                new Task(new Name(EVENT2_NAME), DateTimeUtil.parseStartDateTime(EVENT2_START_DATE_STRING),
                        DateTimeUtil.parseEndDateTime(EVENT2_END_DATE_STRING), new UniqueTagList(), true, false),
                new Task(new Name(DDL2_NAME), null, DateTimeUtil.parseEndDateTime(DDL2_DUE_TIME_STRING),
                        new UniqueTagList("urgent"), false, false),
                new Task(new Name(FLT2_NAME), null, null, new UniqueTagList(), false, false),
                new Task(new Name(EVENT3_NAME), DateTimeUtil.parseStartDateTime(EVENT3_START_DATE_STRING),
                        DateTimeUtil.parseEndDateTime(EVENT3_END_DATE_STRING), new UniqueTagList(), true, false),
                new Task(new Name(DDL3_NAME), null, DateTimeUtil.parseEndDateTime(DDL3_DUE_TIME_STRING),
                        new UniqueTagList(), true, false),
                new Task(new Name(FLT3_NAME), null, null, new UniqueTagList(), false, false),
                new Task(new Name(EVENT4_NAME), DateTimeUtil.parseStartDateTime(EVENT4_START_DATE_STRING),
                        DateTimeUtil.parseEndDateTime(EVENT4_END_DATE_STRING), new UniqueTagList(), true, false),
                new Task(new Name(DDL4_NAME), null, DateTimeUtil.parseEndDateTime(DDL4_DUE_TIME_STRING),
                        new UniqueTagList(), true, false),
                new Task(new Name(FLT4_NAME), null, null, new UniqueTagList(), false, false),
                new Task(new Name(EVENT5_NAME), DateTimeUtil.parseStartDateTime(EVENT5_START_DATE_STRING),
                        DateTimeUtil.parseEndDateTime(EVENT5_END_DATE_STRING), new UniqueTagList(), true, false),
                new Task(new Name(DDL5_NAME), null, DateTimeUtil.parseEndDateTime(DDL5_DUE_TIME_STRING),
                        new UniqueTagList(), true, false),
                new Task(new Name(FLT5_NAME), null, null, new UniqueTagList(), false, false),
                new Task(new Name(EVENT6_NAME), DateTimeUtil.parseStartDateTime(EVENT6_START_DATE_STRING),
                        DateTimeUtil.parseEndDateTime(EVENT6_END_DATE_STRING), new UniqueTagList(), true, false),
                new Task(new Name(DDL6_NAME), null, DateTimeUtil.parseEndDateTime(DDL6_DUE_TIME_STRING),
                        new UniqueTagList(), true, false),
                new Task(new Name(FLT6_NAME), null, null, new UniqueTagList(), false, false),
                new Task(new Name(EVENT7_NAME), DateTimeUtil.parseStartDateTime(EVENT7_START_DATE_STRING),
                        DateTimeUtil.parseEndDateTime(EVENT7_END_DATE_STRING), new UniqueTagList(), true, false),
                new Task(new Name(DDL7_NAME), null, DateTimeUtil.parseEndDateTime(DDL7_DUE_TIME_STRING),
                        new UniqueTagList(), true, false),
                new Task(new Name(FLT7_NAME), null, null, new UniqueTagList(), false, false),
                new Task(new Name(EVENT8_NAME), DateTimeUtil.parseStartDateTime(EVENT8_START_DATE_STRING),
                        DateTimeUtil.parseEndDateTime(EVENT8_END_DATE_STRING), new UniqueTagList(), true, false),
                new Task(new Name(DDL8_NAME), null, DateTimeUtil.parseEndDateTime(DDL8_DUE_TIME_STRING),
                        new UniqueTagList(), false, false),
                new Task(new Name(FLT8_NAME), null, null, new UniqueTagList(), false, false),
                new Task(new Name(EVENT9_NAME), DateTimeUtil.parseStartDateTime(EVENT9_START_DATE_STRING),
                        DateTimeUtil.parseEndDateTime(EVENT9_END_DATE_STRING), new UniqueTagList(), true, false),
                new Task(new Name(DDL9_NAME), null, DateTimeUtil.parseEndDateTime(DDL9_DUE_TIME_STRING),
                        new UniqueTagList(), false, false),
                new Task(new Name(FLT9_NAME), null, null, new UniqueTagList(), false, false),
                new Task(new Name(EVENT10_NAME), DateTimeUtil.parseStartDateTime(EVENT10_START_DATE_STRING),
                        DateTimeUtil.parseEndDateTime(EVENT10_END_DATE_STRING), new UniqueTagList(), true, false),
                new Task(new Name(DDL10_NAME), null, DateTimeUtil.parseEndDateTime(DDL10_DUE_TIME_STRING),
                        new UniqueTagList(), false, false),
                new Task(new Name(FLT10_NAME), null, null, new UniqueTagList(), false, false),
                new Task(new Name(EVENT11_NAME), DateTimeUtil.parseStartDateTime(EVENT11_START_DATE_STRING),
                        DateTimeUtil.parseEndDateTime(EVENT11_END_DATE_STRING), new UniqueTagList(), true, false),
                new Task(new Name(DDL11_NAME), null, DateTimeUtil.parseEndDateTime(DDL11_DUE_TIME_STRING),
                        new UniqueTagList(), false, false),
                new Task(new Name(FLT11_NAME), null, null, new UniqueTagList(), false, false),
                new Task(new Name(EVENT12_NAME), DateTimeUtil.parseStartDateTime(EVENT12_START_DATE_STRING),
                        DateTimeUtil.parseEndDateTime(EVENT12_END_DATE_STRING), new UniqueTagList(), true, false),
                new Task(new Name(DDL12_NAME), null, DateTimeUtil.parseEndDateTime(DDL12_DUE_TIME_STRING),
                        new UniqueTagList(), false, false),
                new Task(new Name(FLT12_NAME), null, null, new UniqueTagList(), false, false),
                new Task(new Name(EVENT13_NAME), DateTimeUtil.parseStartDateTime(EVENT13_START_DATE_STRING),
                        DateTimeUtil.parseEndDateTime(EVENT13_END_DATE_STRING), new UniqueTagList(), false, false),
                new Task(new Name(DDL13_NAME), null, DateTimeUtil.parseEndDateTime(DDL13_DUE_TIME_STRING),
                        new UniqueTagList(), true, false),
                new Task(new Name(FLT13_NAME), null, null, new UniqueTagList(), false, false),
                new Task(new Name(EVENT14_NAME), DateTimeUtil.parseStartDateTime(EVENT14_START_DATE_STRING),
                        DateTimeUtil.parseEndDateTime(EVENT14_END_DATE_STRING), new UniqueTagList(), false, false),
                new Task(new Name(DDL14_NAME), null, DateTimeUtil.parseEndDateTime(DDL14_DUE_TIME_STRING),
                        new UniqueTagList(), true, false),
                new Task(new Name(FLT14_NAME), null, null, new UniqueTagList(), false, false),
                new Task(new Name(EVENT15_NAME), DateTimeUtil.parseStartDateTime(EVENT15_START_DATE_STRING),
                        DateTimeUtil.parseEndDateTime(EVENT15_END_DATE_STRING), new UniqueTagList(), false, false),
                new Task(new Name(DDL15_NAME), null, DateTimeUtil.parseEndDateTime(DDL15_DUE_TIME_STRING),
                        new UniqueTagList(), false, false),
                new Task(new Name(FLT15_NAME), null, null, new UniqueTagList(), false, false),
                new Task(new Name(EVENT16_NAME), DateTimeUtil.parseStartDateTime(EVENT16_START_DATE_STRING),
                        DateTimeUtil.parseEndDateTime(EVENT16_END_DATE_STRING), new UniqueTagList(), false, false),
                new Task(new Name(DDL16_NAME), null, DateTimeUtil.parseEndDateTime(DDL16_DUE_TIME_STRING),
                        new UniqueTagList(), true, false),
                new Task(new Name(FLT16_NAME), null, null, new UniqueTagList(), false, false),
                new Task(new Name(EVENT17_NAME), DateTimeUtil.parseStartDateTime(EVENT17_START_DATE_STRING),
                        DateTimeUtil.parseEndDateTime(EVENT3_END_DATE_STRING), new UniqueTagList(), false, false),
                new Task(new Name(DDL17_NAME), null, DateTimeUtil.parseEndDateTime(DDL17_DUE_TIME_STRING),
                        new UniqueTagList(), false, false),
                new Task(new Name(FLT17_NAME), null, null, new UniqueTagList(), false, false)
            };
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

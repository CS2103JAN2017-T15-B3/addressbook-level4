// @@author A0130277L

package seedu.taskmanager.logic.parser;

import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

import seedu.taskmanager.commons.exceptions.IllegalValueException;
import seedu.taskmanager.model.task.ReadOnlyTask;
import seedu.taskmanager.model.task.Task;
import seedu.taskmanager.model.task.TaskDate;

/**
 * Natty date parser that parses a command with date and time and return a Date object
 */
public class DateTimeUtil {

    public static final String INVALID_DATE_FORMAT = "Date format is not accepted by PotaTodo";
    public static final String EMPTY_STRING = "";

    private static final String EXPLICIT_TIME_SYNTAX = "EXPLICIT_TIME";
    private static final String RELATIVE_TIME_SYNTAX = "RELATIVE_TIME";
    private static final String NOW_SYNTAX = "SEEK > by_day 0 day";

    private static final int FIRST_ELEMENT_INDEX = 0;

    private static final int STARTING_TIME_HOUR = 0;
    private static final int STARTING_TIME_MINUTE = 0;
    private static final int STARTING_TIME_SECOND = 0;
    private static final int ENDING_TIME_HOUR = 23;
    private static final int ENDING_TIME_MINUTE = 59;
    private static final int ENDING_TIME_SECOND = 59;

    private static final boolean TIME_PRESENT = true;
    private static final boolean TIME_ABSENT = false;

    // Used to store and print date to end user.
    public static final String DATE_STRING_FORMAT = "dd MMMMM yyyy, hh:mm aaa";
    public static final String ONLY_DATE_STRING_FORMAT = "dd MMMM yyy";

    private static Parser dateTimeParser = new Parser(TimeZone.getDefault());

    // General date/time parses for string with both date and time elements
    public static TaskDate parseDateTime(String date) throws IllegalValueException {
        assertNotNull(date);
        List<DateGroup> parsedDates = dateTimeParser.parse(date);

        if (isValidArg(parsedDates)) {
            return new TaskDate(parsedDates.get(FIRST_ELEMENT_INDEX).getDates().get(FIRST_ELEMENT_INDEX), TIME_PRESENT);

        } else {
            throw new IllegalValueException(INVALID_DATE_FORMAT);
        }
    }

    // Specialized date/time parser for startDate string with only date element
    // Set time of the returned date object as the starting time of the day
    // i.e. 00:00:00
    public static TaskDate parseStartDateTime(String startDate) throws IllegalValueException {
        assertNotNull(startDate);
        List<DateGroup> parsedStartDatesList = dateTimeParser.parse(startDate);

        if (isValidArg(parsedStartDatesList)) {

            DateGroup parsedStartDate = parsedStartDatesList.get(FIRST_ELEMENT_INDEX);

            if (!isTimePresent(startDate)) {
                return new TaskDate(setStartDateTime(parsedStartDate.getDates().get(FIRST_ELEMENT_INDEX)), TIME_ABSENT);
            }
            return new TaskDate(parsedStartDate.getDates().get(FIRST_ELEMENT_INDEX), TIME_PRESENT);

        } else {
            throw new IllegalValueException(INVALID_DATE_FORMAT);
        }
    }

    // Specialized date/time parser for endDate string with only date element
    // Set time of the returned date object as the ending time of the day
    // i.e. 23:59:59
    public static TaskDate parseEndDateTime(String endDate) throws IllegalValueException {
        assertNotNull(endDate);
        List<DateGroup> parsedEndDatesList = dateTimeParser.parse(endDate);

        if (isValidArg(parsedEndDatesList)) {

            DateGroup parsedEndDate = parsedEndDatesList.get(FIRST_ELEMENT_INDEX);

            if (!isTimePresent(endDate)) {
                return new TaskDate(setEndDateTime(parsedEndDate.getDates().get(FIRST_ELEMENT_INDEX)), TIME_ABSENT);
            }
            return new TaskDate(parsedEndDate.getDates().get(FIRST_ELEMENT_INDEX), TIME_PRESENT);

        } else {
            throw new IllegalValueException(INVALID_DATE_FORMAT);
        }
    }

    // toString method for date objects
    // @returns both date element and time element
    public static String getStringFromDate(Date date) {
        assertNotNull(date);
        DateFormat dateFormat = new SimpleDateFormat(DATE_STRING_FORMAT);
        return dateFormat.format(date);
    }

    // toString method for date objects
    // @return only returns only date element without time element
    public static String getOnlyDateStringFromDate(Date date) {
        assertNotNull(date);
        DateFormat onlyDateFormat = new SimpleDateFormat(ONLY_DATE_STRING_FORMAT);
        return onlyDateFormat.format(date);
    }

    // Check if the DateGroup argument input is valid
    private static boolean isValidArg(List<DateGroup> parsedDatesList) {
        if (parsedDatesList != null && !parsedDatesList.isEmpty()) {
            return true;
        }
        return false;
    }

    // Check if explicit time or relative time is present in a given date/time string
    public static boolean isTimePresent(String date) {
        assertNotNull(date);
        List<DateGroup> parsedDatesList = dateTimeParser.parse(date);
        assert isValidArg(parsedDatesList);
        DateGroup parsedDate = parsedDatesList.get(FIRST_ELEMENT_INDEX);
        String syntaxTreeString = parsedDate.getSyntaxTree().getChild(FIRST_ELEMENT_INDEX).toStringTree();
        return syntaxTreeString.contains(EXPLICIT_TIME_SYNTAX) || syntaxTreeString.contains(RELATIVE_TIME_SYNTAX)
                || syntaxTreeString.contains(NOW_SYNTAX);
    }

    /**
     * Checks if a particular string is a valid time format
     * @param date
     * @return true if string is parseable to date, false otherwise
     */
    public static boolean isValidDateString(String args) {
        assertNotNull(args);
        List<DateGroup> parsedString = dateTimeParser.parse(args);
        return isValidArg(parsedString);
    }

    // Set time of the returned Date object as the starting time of the day
    // i.e. 00:00:00
    private static Date setStartDateTime(Date date) {
        assertNotNull(date);
        return setPredefinedTime(date, STARTING_TIME_HOUR, STARTING_TIME_MINUTE, STARTING_TIME_SECOND);
    }

    // Set time of the returned Date object as the ending time of the day
    // i.e. 23:59:59
    private static Date setEndDateTime(Date date) {
        assertNotNull(date);
        return setPredefinedTime(date, ENDING_TIME_HOUR, ENDING_TIME_MINUTE, ENDING_TIME_SECOND);
    }

    private static Date setPredefinedTime(Date date, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        return cal.getTime();
    }

    // Check if two tasks are conflicting each other
    public static boolean isConflicting(Task taskToBeChecked, ReadOnlyTask taskToBeComparedWith) {
        if (!taskToBeComparedWith.isEvent() || taskToBeComparedWith.isDone() || !taskToBeChecked.isEvent()
                || taskToBeChecked.isDone()) {
            return false;
        }
        Date startDateToBeChecked = taskToBeChecked.getStartDate().getTaskDate();
        Date endDateToBeChecked = taskToBeChecked.getEndDate().getTaskDate();
        Date startDateToBeComparedWith = taskToBeComparedWith.getStartDate().getTaskDate();
        Date endDateToBeComparedWith = taskToBeComparedWith.getEndDate().getTaskDate();

        return !startDateToBeChecked.after(endDateToBeComparedWith)
                && !startDateToBeComparedWith.after(endDateToBeChecked);
    }
}

package seedu.taskmanager.logic.parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

import seedu.taskmanager.commons.exceptions.IllegalValueException;

/**
 * Natty date parser that parses a command with date and time and return a Date object
 */

public class DateTimeUtil {

    public static final String INVALID_DATE_FORMAT = "Date format is not accepted by PotaTodo";
    public static final String EMPTY_STRING = "";

    private static final String TIME_SYNTAX = "EXPLICIT_TIME";
    private static final int FIRST_ELEMENT_INDEX = 0;

    private static final int STARTING_TIME_HOUR = 0;
    private static final int STARTING_TIME_MINUTE = 0;
    private static final int STARTING_TIME_SECOND = 0;
    private static final int ENDING_TIME_HOUR = 23;
    private static final int ENDING_TIME_MINUTE = 59;
    private static final int ENDING_TIME_SECOND = 59;

    // Used to store and print date to end user.
    public static final String DATE_STRING_FORMAT = "dd MMMMM yyyy, hh:mm aaa";

    public DateTimeUtil() {
    };

    private static Parser dateTimeParser = new Parser(TimeZone.getDefault());

    //General date/time parses for string with both date and time elements
    public static Date parseDateTime(String date) throws IllegalValueException {
        List<DateGroup> parsedDates = dateTimeParser.parse(date);

        if (isValidArg(parsedDates)) {
            return parsedDates.get(FIRST_ELEMENT_INDEX).getDates().get(FIRST_ELEMENT_INDEX);

        } else {
            throw new IllegalValueException(INVALID_DATE_FORMAT);
        }
    }
    
    //Specialized date/time parser for startDate string with only date element
    //Set time of the returned date object as the starting time of the day
    //i.e. 00:00:00 am
    public static Date parseStartDateTime(String startDate) throws IllegalValueException {
    	List<DateGroup> parsedStartDatesList = dateTimeParser.parse(startDate);

    	if (isValidArg(parsedStartDatesList)) {

    		DateGroup parsedStartDate = parsedStartDatesList.get(FIRST_ELEMENT_INDEX);
    		String syntaxTreeString = parsedStartDate.getSyntaxTree().getChild(FIRST_ELEMENT_INDEX).toStringTree();
    		
    		if (!isTimePresent(syntaxTreeString)) {
    			return setStartDateTime(parsedStartDate.getDates().get(FIRST_ELEMENT_INDEX));
    		}
    		return parsedStartDate.getDates().get(FIRST_ELEMENT_INDEX);

    	} else {
    		throw new IllegalValueException(INVALID_DATE_FORMAT);
    	}
    }

    //Specialized date/time parser for endDate string with only date element
    //Set time of the returned date object as the ending time of the day
    //i.e. 11:59:59 pm
    public static Date parseEndDateTime(String endDate) throws IllegalValueException {
    	List<DateGroup> parsedEndDatesList = dateTimeParser.parse(endDate);

    	if (isValidArg(parsedEndDatesList)) {

    		DateGroup parsedEndDate = parsedEndDatesList.get(FIRST_ELEMENT_INDEX);
    		String syntaxTreeString = parsedEndDate.getSyntaxTree().getChild(FIRST_ELEMENT_INDEX).toStringTree();
    		
    		if (!isTimePresent(syntaxTreeString)) {
    			return setEndDateTime(parsedEndDate.getDates().get(FIRST_ELEMENT_INDEX));
    		}
    		return parsedEndDate.getDates().get(FIRST_ELEMENT_INDEX);

    	} else {
    		throw new IllegalValueException(INVALID_DATE_FORMAT);
    	}
    }

    public static String getStringFromDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_STRING_FORMAT);
        return dateFormat.format(date);
    }

    //Check if the DateGroup argument input is valid
    private static boolean isValidArg(List<DateGroup> parsedDatesList) {
        if (parsedDatesList != null && !parsedDatesList.isEmpty()) {
                return true;
        } else {
                return false;
        }
    }
    
    //Check if explicit time is present in the syntax tree
    private static boolean isTimePresent(String syntaxTreeString) {
    	return syntaxTreeString.contains(TIME_SYNTAX);
    }
    
    //Set time of the returned Date object as the starting time of the day
    //i.e. 00:00:00
    private static Date setStartDateTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, STARTING_TIME_HOUR);
        cal.set(Calendar.MINUTE, STARTING_TIME_MINUTE);
        cal.set(Calendar.SECOND, STARTING_TIME_SECOND);
        return cal.getTime();
    }

    //Set time of the returned Date object as the ending time of the day
    //i.e. 23:59:59
    private static Date setEndDateTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, ENDING_TIME_HOUR);
        cal.set(Calendar.MINUTE, ENDING_TIME_MINUTE);
        cal.set(Calendar.SECOND, ENDING_TIME_SECOND);
        return cal.getTime();
    }
}

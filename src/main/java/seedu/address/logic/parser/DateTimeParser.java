package seedu.address.logic.parser;

import java.util.Date;
import java.util.TimeZone;
import java.util.List;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

/*
 * A parser that utilized external library (Natty Date by Joe Stelmach)
 * to parse string into Date object.
 * @Throws an exception when there is no or are more than one date-time detected.
 */
public class DateTimeParser {
    
    public DateTimeParser (){};
    
    private static Parser dateTimeParser = new Parser(TimeZone.getDefault());
    
    public static Date parseDateTime(String date){
        
        List<DateGroup> dateTimeList = dateTimeParser.parse(date);
        
        if( dateTimeList == null || dateTimeList.isEmpty() || dateTimeList.size() > 1)
            return null;
        else
            return dateTimeList.get(0).getDates().get(0);
    }
}
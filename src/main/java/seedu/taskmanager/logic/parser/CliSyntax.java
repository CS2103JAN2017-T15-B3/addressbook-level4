
package seedu.taskmanager.logic.parser;

import java.util.regex.Pattern;

import seedu.taskmanager.logic.parser.ArgumentTokenizer.Prefix;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    // @@author A0140417R
    /* Prefix definitions */
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_STARTDATE = new Prefix("s/");
    public static final Prefix PREFIX_ENDDATE = new Prefix("e/");

    /* Accepted words for date and time */
    public static final String[] ACCEPTED_START_DATE_MARKER = {
        "from"
    };
    public static final String[] ACCEPTED_END_DATE_MARKER = {
        "to",
        "by",
        "at"
    };

    /* Patterns definitions */
    public static final Pattern KEYWORDS_ARGS_FORMAT = Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one
                                                                                                          // or
                                                                                                          // more
                                                                                                          // keywords
                                                                                                          // separated
                                                                                                          // by
                                                                                                          // whitespace

}


package seedu.taskmanager.logic.parser;

import static seedu.taskmanager.commons.util.CommonStringUtil.DEADLINE_STRING;
import static seedu.taskmanager.commons.util.CommonStringUtil.DONE_STRING;
import static seedu.taskmanager.commons.util.CommonStringUtil.EMPTY_STRING;
import static seedu.taskmanager.commons.util.CommonStringUtil.EVENT_STRING;
import static seedu.taskmanager.commons.util.CommonStringUtil.FLOATING_TASK_STRING;
import static seedu.taskmanager.commons.util.CommonStringUtil.UNDONE_STRING;

import java.util.ArrayList;

import seedu.taskmanager.commons.exceptions.IllegalValueException;

// @@author A0140538J
/**
 * A util for parsing of list command arguments. Contains the accepted arguments following a list command.
 */
public class ListArgumentsUtil {

    public ArrayList<String> acceptedWords = new ArrayList<String>();
    public boolean isDate = true;

    public ListArgumentsUtil(String arg) {

        acceptedWords.add(EMPTY_STRING);
        acceptedWords.add(FLOATING_TASK_STRING);
        acceptedWords.add(DEADLINE_STRING);
        acceptedWords.add(EVENT_STRING);
        acceptedWords.add(DONE_STRING);
        acceptedWords.add(UNDONE_STRING);

        try {
            DateTimeUtil.parseDateTime(arg);
        } catch (IllegalValueException ive) {
            isDate = false;
        }
    }
}

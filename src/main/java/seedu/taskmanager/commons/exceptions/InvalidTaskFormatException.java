package seedu.taskmanager.commons.exceptions;

/**
 * Signals an error when task format is not in the right date structure.
 * Floating: no startDate & endDate Deadline: no startDate, has endDate Event:
 * has startDate & endDate
 */
public class InvalidTaskFormatException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 5105408959983137495L;

    public InvalidTaskFormatException(String message) {
        super(message);
    }
}

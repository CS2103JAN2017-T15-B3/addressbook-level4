package seedu.taskmanager.commons.exceptions;

/**
 * Signals an error caused by duplicate data where there should be none.
 */
public abstract class NoTaskDateException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 3865712563156011916L;

    public NoTaskDateException(String message) {
        super(message);
    }
}

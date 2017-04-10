
package seedu.taskmanager.logic.commands;

import static org.junit.Assert.assertNotNull;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    public final String feedbackToUser;

    public CommandResult(String feedbackToUser) {
        assertNotNull(feedbackToUser);
        this.feedbackToUser = feedbackToUser;
    }

}


package seedu.taskmanager.logic.parser;

import static org.junit.Assert.assertNotNull;

import static seedu.taskmanager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskmanager.commons.util.CommonStringUtil.DONE_STRING;
import static seedu.taskmanager.commons.util.CommonStringUtil.UNDONE_STRING;

import java.util.List;
import java.util.Optional;

import seedu.taskmanager.logic.commands.Command;
import seedu.taskmanager.logic.commands.IncorrectCommand;
import seedu.taskmanager.logic.commands.MarkCommand;

// @@author A0140538J
/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkCommand and returns a MarkCommand object
     * for execution.
     */
    public Command parse(String args) {
        assertNotNull(args);

        ArgumentTokenizer argsTokenizer = new ArgumentTokenizer();
        argsTokenizer.tokenize(args);

        List<Optional<String>> preambleFields = ParserUtil.splitPreamble(argsTokenizer.getPreamble().orElse(""), 2);

        Optional<Integer> index = preambleFields.get(0).flatMap(ParserUtil::parseIndex);
        if (!index.isPresent()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        }

        if (!preambleFields.get(1).isPresent()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        }

        if (!preambleFields.get(1).get().toLowerCase().equals(DONE_STRING)
                && !preambleFields.get(1).get().toLowerCase().equals(UNDONE_STRING)) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        }

        return new MarkCommand(index.get(), preambleFields.get(1).get().toLowerCase());
    }
}

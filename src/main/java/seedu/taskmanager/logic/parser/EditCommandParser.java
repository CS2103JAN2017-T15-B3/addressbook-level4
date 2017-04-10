
package seedu.taskmanager.logic.parser;

import static org.junit.Assert.assertNotNull;

import static seedu.taskmanager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskmanager.commons.util.CommonStringUtil.REMOVE_STRING;
import static seedu.taskmanager.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.taskmanager.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static seedu.taskmanager.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import seedu.taskmanager.commons.exceptions.IllegalValueException;
import seedu.taskmanager.logic.commands.Command;
import seedu.taskmanager.logic.commands.EditCommand;
import seedu.taskmanager.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.taskmanager.logic.commands.IncorrectCommand;
import seedu.taskmanager.model.tag.UniqueTagList;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser {

    // @@author A0140538J
    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand and returns an EditCommand object
     * for execution.
     */
    public Command parse(String args) {
        assertNotNull(args);

        ArgumentTokenizer argsTokenizer = new ArgumentTokenizer(PREFIX_STARTDATE, PREFIX_ENDDATE, PREFIX_TAG);
        Optional<Integer> index;
        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();

        try {
            args = DateMarkerParser.replaceMarkersWithPrefix(args);
            argsTokenizer.tokenize(args);

            List<Optional<String>> preambleFields = ParserUtil.splitPreamble(argsTokenizer.getPreamble().orElse(""), 2);

            index = preambleFields.get(0).flatMap(ParserUtil::parseIndex);
            if (!index.isPresent()) {
                return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
            }
            // @@author A0140417R
            Optional<String> startDateString = argsTokenizer.getValue(PREFIX_STARTDATE);
            Optional<String> endDateString = argsTokenizer.getValue(PREFIX_ENDDATE);

            if (startDateString.isPresent()) {
                if (isRemoveString(startDateString)) {
                    editTaskDescriptor.setStartDateRemovedFlag();

                } else {
                    editTaskDescriptor.setStartDate(ParserUtil.parseStartTaskDate(startDateString));
                }
            }

            if (endDateString.isPresent()) {
                if (isRemoveString(endDateString)) {
                    editTaskDescriptor.setEndDateRemovedFlag();
                } else {
                    editTaskDescriptor.setEndDate(ParserUtil.parseEndTaskDate(endDateString));
                }
            }

            editTaskDescriptor.setName(ParserUtil.parseName(preambleFields.get(1)));
            editTaskDescriptor.setTags(parseTagsForEdit(ParserUtil.toSet(argsTokenizer.getAllValues(PREFIX_TAG))));

        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            return new IncorrectCommand(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index.get(), editTaskDescriptor);
    }

    private boolean isRemoveString(Optional<String> dateString) {
        return dateString.get().substring(1).trim().toLowerCase().equals(REMOVE_STRING);
    }
    // @@author

    /**
     * Parses {@code Collection<String> tags} into an {@code Optional<UniqueTagList>} if {@code tags} is non-empty. If
     * {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Optional<UniqueTagList>} containing zero tags.
     */
    private Optional<UniqueTagList> parseTagsForEdit(Collection<String> tags) throws IllegalValueException {
        assertNotNull(tags);

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}

package seedu.taskmanager.logic.parser;

import static seedu.taskmanager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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

    public static final String EMPTY_STRING = "";
    public static final String REMOVE_STRING = "/remove";

    /**
     * Parses the given {@code String} of arguments in the context of the
     * EditCommand and returns an EditCommand object for execution.
     */
    public Command parse(String args) {

        assert args != null;
        ArgumentTokenizer argsTokenizer = new ArgumentTokenizer(PREFIX_STARTDATE, PREFIX_ENDDATE, PREFIX_TAG);
        argsTokenizer.tokenize(args);

        List<Optional<String>> preambleFields = ParserUtil.splitPreamble(argsTokenizer.getPreamble().orElse(""), 2);

        Optional<Integer> index = preambleFields.get(0).flatMap(ParserUtil::parseIndex);
        if (!index.isPresent()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();

        try {
        	editTaskDescriptor.setName(ParserUtil.parseName(preambleFields.get(1)));

        	Optional<String> startDateString = argsTokenizer.getValue(PREFIX_STARTDATE);
            if (argsTokenizer.getValue(PREFIX_STARTDATE).isPresent()) {
            	if (!argsTokenizer.getValue(PREFIX_STARTDATE).get().equals(REMOVE_STRING)) {
            		editTaskDescriptor.setStartDate(ParserUtil.parseTaskDate(startDateString));
                }
            }
            editTaskDescriptor.setStartDate(emptyStartDate);

            Optional<String> endDateString = argsTokenizer.getValue(PREFIX_ENDDATE);
            if (argsTokenizer.getValue(PREFIX_ENDDATE).isPresent()) {
            	if (!argsTokenizer.getValue(PREFIX_ENDDATE).get().equals(REMOVE_STRING)) {
            		editTaskDescriptor.setEndDate(ParserUtil.parseTaskDate(endDateString));
                }
            }
            editTaskDescriptor.setStartDate(emptyEndDate);


            if (startDateString.isPresent() && startDateString.get().toLowerCase().equals(REMOVE_STRING)) {
                editTaskDescriptor.setStartDateRemovedFlag();

            } else {
                editTaskDescriptor.setStartDate(ParserUtil.parseTaskDate(startDateString));
            }

            if (endDateString.isPresent() && endDateString.get().toLowerCase().equals(REMOVE_STRING)) {
                editTaskDescriptor.setEndDateRemovedFlag();

            } else {
                editTaskDescriptor.setEndDate(ParserUtil.parseTaskDate(endDateString));
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

    /**
     * Parses {@code Collection<String> tags} into an
     * {@code Optional<UniqueTagList>} if {@code tags} is non-empty. If
     * {@code tags} contain only one element which is an empty string, it will
     * be parsed into a {@code Optional<UniqueTagList>} containing zero tags.
     */
    private Optional<UniqueTagList> parseTagsForEdit(Collection<String> tags) throws IllegalValueException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}

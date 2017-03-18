package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ListCommand;

public class ListCommandParser {
	
	/**
     * Parses the given argument in the context of the ListCommand
     * and returns an ListCommand object for execution.
     */
	public Command parse(String args) {
		
		return new ListCommand(args.trim());
	}
}

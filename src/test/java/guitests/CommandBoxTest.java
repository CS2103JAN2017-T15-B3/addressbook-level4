
package guitests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import seedu.taskmanager.ui.CommandBox;

public class CommandBoxTest extends TaskManagerGuiTest {

    private static final String COMMAND_THAT_SUCCEEDS = "mark 3 done";
    private static final String ANOTHER_COMMAND_THAT_SUCCEEDS = "add valid name s/today e/next week";
    private static final String COMMAND_THAT_FAILS = "invalid command";

    private static final String FIRST_INDEXT_COMMAND_STRING = "";

    private ArrayList<String> defaultStyleOfCommandBox;
    private ArrayList<String> errorStyleOfCommandBox;

    @Before
    public void setUp() {
        defaultStyleOfCommandBox = new ArrayList<>(commandBox.getStyleClass());
        assertFalse("CommandBox default style classes should not contain error style class.",
                defaultStyleOfCommandBox.contains(CommandBox.ERROR_STYLE_CLASS));

        // build style class for error
        errorStyleOfCommandBox = new ArrayList<>(defaultStyleOfCommandBox);
        errorStyleOfCommandBox.add(CommandBox.ERROR_STYLE_CLASS);
    }

    @Test
    public void commandBox_commandSucceeds_textClearedAndStyleClassRemainsTheSame() {
        commandBox.runCommand(COMMAND_THAT_SUCCEEDS);

        assertEquals("", commandBox.getCommandInput());
        assertEquals(defaultStyleOfCommandBox, commandBox.getStyleClass());
    }

    @Test
    public void commandBox_commandFails_textStaysAndErrorStyleClassAdded() {
        commandBox.runCommand(COMMAND_THAT_FAILS);

        assertEquals(COMMAND_THAT_FAILS, commandBox.getCommandInput());
        assertEquals(errorStyleOfCommandBox, commandBox.getStyleClass());
    }

    @Test
    public void commandBox_commandSucceedsAfterFailedCommand_textClearedAndErrorStyleClassRemoved() {
        // add error style to simulate a failed command
        commandBox.getStyleClass().add(CommandBox.ERROR_STYLE_CLASS);

        commandBox.runCommand(COMMAND_THAT_SUCCEEDS);

        assertEquals("", commandBox.getCommandInput());
        assertEquals(defaultStyleOfCommandBox, commandBox.getStyleClass());
    }

    // @@author A0130277L
    @Test
    public void commandBox_previousCommand() {
        commandBox.runCommand(COMMAND_THAT_SUCCEEDS);
        commandBox.runCommand(ANOTHER_COMMAND_THAT_SUCCEEDS);
        commandBox.previousCommand();
        assertEquals(ANOTHER_COMMAND_THAT_SUCCEEDS, commandBox.getCommandInput());
        commandBox.previousCommand();
        assertEquals(COMMAND_THAT_SUCCEEDS, commandBox.getCommandInput());
    }

    @Test
    public void commandBox_nextCommand() {
        commandBox.runCommand(COMMAND_THAT_SUCCEEDS);
        commandBox.runCommand(ANOTHER_COMMAND_THAT_SUCCEEDS);
        commandBox.previousCommand();
        commandBox.previousCommand();
        commandBox.nextCommand();
        assertEquals(ANOTHER_COMMAND_THAT_SUCCEEDS, commandBox.getCommandInput());
    }

    @Test
    public void commandBox_previousCommand_exceedingIndex() {
        commandBox.runCommand(COMMAND_THAT_SUCCEEDS);
        commandBox.runCommand(ANOTHER_COMMAND_THAT_SUCCEEDS);
        commandBox.previousCommand();
        commandBox.previousCommand();
        commandBox.previousCommand();
        assertEquals(COMMAND_THAT_SUCCEEDS, commandBox.getCommandInput());
        commandBox.previousCommand();
        assertEquals(COMMAND_THAT_SUCCEEDS, commandBox.getCommandInput());
    }

    @Test
    public void commandBox_nextCommand_exceedingIndex() {
        commandBox.runCommand(COMMAND_THAT_SUCCEEDS);
        commandBox.runCommand(ANOTHER_COMMAND_THAT_SUCCEEDS);
        commandBox.previousCommand();
        commandBox.previousCommand();
        commandBox.nextCommand();
        commandBox.nextCommand();
        commandBox.nextCommand();
        assertEquals(FIRST_INDEXT_COMMAND_STRING, commandBox.getCommandInput());
    }
    // @@author

}

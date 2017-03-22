package seedu.taskmanager.logic;

import static seedu.taskmanager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import org.junit.Test;

import seedu.taskmanager.logic.commands.EditCommand;
import seedu.taskmanager.model.TaskManager;
import seedu.taskmanager.model.task.Task;

public class LogicEditCommandTest extends LogicManagerTest {

	@Test
	public void execute_edit_invalid() throws Exception {
		String invalidCommand = "edit meeting";
		String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
		assertCommandFailure(invalidCommand, expectedMessage);
	}

	@Test
	public void execute_edit_nameValid() throws Exception {

		TestDataHelper helper = new TestDataHelper();
        Task task1 = helper.generateTaskWithName("potato");
        Task task2 = helper.generateTaskWithName("pineapple");
        Task editedTask2 = helper.generateTaskWithName("lol");

        String expectedMessage = String.format(EditCommand.MESSAGE_SUCCESS, editedTask2);

        List<Task> sampleTasks = helper.generateTaskList(task1,task2);
        TaskManager expectedTM = helper.generateTaskManager(sampleTasks);
        List<Task> expectedList = helper.generateTaskList(task1,editedTask2);
        helper.addToModel(model, sampleTasks);

        assertCommandSuccess("edit 2 lol", expectedMessage, expectedTM, expectedList);
	}
}

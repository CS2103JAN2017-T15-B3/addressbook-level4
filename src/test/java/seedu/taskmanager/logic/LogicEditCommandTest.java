package seedu.taskmanager.logic;

import static seedu.taskmanager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import org.junit.Test;

import seedu.taskmanager.commons.core.Messages;
import seedu.taskmanager.logic.commands.EditCommand;
import seedu.taskmanager.logic.parser.DateTimeUtil;
import seedu.taskmanager.model.TaskManager;
import seedu.taskmanager.model.task.DummyStartTaskDate;
import seedu.taskmanager.model.task.Task;

public class LogicEditCommandTest extends LogicManagerTest {

    @Test
    public void execute_edit_blank() throws Exception {
        String invalidCommand = "edit";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
        assertCommandFailure(invalidCommand, expectedMessage);
    }

    @Test
    public void execute_edit_missingIndex() throws Exception {
        String invalidCommand = "edit meeting";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
        assertCommandFailure(invalidCommand, expectedMessage);
    }

    @Test
    public void execute_edit_noArguments() throws Exception {
        String invalidCommand = "edit 1";
        String expectedMessage = EditCommand.MESSAGE_NOT_EDITED;
        assertCommandFailure(invalidCommand, expectedMessage);
    }

    @Test
    public void execute_edit_duplicatedName() throws Exception {
        // setup expectations
        TestDataHelper helper = new TestDataHelper();
        Task task1 = helper.meeting();
        Task task2 = helper.homework();

        // setup starting state
        model.addTask(task1); // task already in internal task manager
        model.addTask(task2);

        // execute command and verify result
        String invalidCommand = "edit 1 CS Homework";
        String expectedMessage = EditCommand.MESSAGE_DUPLICATE_TASK;
        assertCommandFailure(invalidCommand, expectedMessage);
    }

    @Test
    public void execute_edit_invalidIndex() throws Exception {
        // setup expectations
        TestDataHelper helper = new TestDataHelper();
        Task task1 = helper.meeting();

        // setup starting state is empty task manager

        // execute command and verify result
        String expectedMessage = Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
        String invalidCommand1 = "edit 1 birthday";
        assertCommandFailure(invalidCommand1, expectedMessage);

        model.addTask(task1); // task already in internal task manager
        String invalidCommand2 = "edit 2 birthday";
        assertCommandFailure(invalidCommand2, expectedMessage);
    }

    @Test
    public void execute_edit_invalidDateFormat() throws Exception {
        // setup expectations
        TestDataHelper helper = new TestDataHelper();
        Task task1 = helper.meeting();

        // setup starting state
        model.addTask(task1); // task already in internal task manager

        // execute command and verify result
        String expectedMessage = DateTimeUtil.INVALID_DATE_FORMAT;
        String invalidCommand1 = "edit 1 s/now e/potato";
        assertCommandFailure(invalidCommand1, expectedMessage);

        String invalidCommand2 = "edit 1 s/!@#$%^";
        assertCommandFailure(invalidCommand2, expectedMessage);
    }

    @Test
    public void execute_edit_startAfterEndDate() throws Exception {
        // setup expectations
        TestDataHelper helper = new TestDataHelper();
        Task task = helper.meeting();

        // setup starting state
        model.addTask(task); // task already in internal task manager

        // execute command and verify result
        String expectedMessage = Messages.MESSAGE_START_AFTER_END;
        String invalidCommand = "edit 1 e/1/1/2017";
        assertCommandFailure(invalidCommand, expectedMessage);
    }

    @Test
    public void execute_edit_validName() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Task task1 = helper.generateTaskWithName("potato");
        Task task2 = helper.generateTaskWithName("pineapple");
        Task editedTask2 = helper.generateTaskWithName("lol");

        List<Task> sampleTasks = helper.generateTaskList(task1, task2);
        TaskManager expectedTM = helper.generateTaskManager(sampleTasks);
        List<Task> expectedList = helper.generateTaskList(task1, editedTask2);
        helper.addToModel(model, sampleTasks);

        String expectedMessage = String.format(EditCommand.MESSAGE_SUCCESS, editedTask2);
        String validCommand = "edit 2 lol";
        assertCommandSuccess(validCommand, expectedMessage, expectedTM, expectedList);
    }

    @Test
    public void execute_edit_validStartDate() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Task task = helper.generateTaskWithAll("potato", "now", "12/12/2012 12am");
        Task editedTask = helper.generateTaskWithAll("potato", "11/11/2011 11am", "12/12/2012 12am");

        List<Task> sampleTasks = helper.generateTaskList(task);
        TaskManager expectedTM = helper.generateTaskManager(sampleTasks);
        List<Task> expectedList = helper.generateTaskList(editedTask);
        helper.addToModel(model, sampleTasks);

        String expectedMessage = String.format(EditCommand.MESSAGE_SUCCESS, editedTask);
        String validCommand = "edit 1 s/11/11/2011 11am";
        assertCommandSuccess(validCommand, expectedMessage, expectedTM, expectedList);
    }

    @Test
    public void execute_edit_validEndDate() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Task task = helper.generateTaskWithName("potato");
        Task editedTask = helper.generateTaskWithAll("potato", DummyStartTaskDate.DUMMY_START_DATE_STRING, "now");

        List<Task> sampleTasks = helper.generateTaskList(task);
        TaskManager expectedTM = helper.generateTaskManager(sampleTasks);
        List<Task> expectedList = helper.generateTaskList(editedTask);
        helper.addToModel(model, sampleTasks);

        String expectedMessage = String.format(EditCommand.MESSAGE_SUCCESS, editedTask);
        String validCommand = "edit 1 e/now";
        assertCommandSuccess(validCommand, expectedMessage, expectedTM, expectedList);
    }

    @Test
    public void execute_edit_validAll() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Task task = helper.generateTaskWithName("kantang");
        Task editedTask = helper.generateTaskWithAll("potato", "now", "1/2/2023 4.56pm");

        List<Task> sampleTasks = helper.generateTaskList(task);
        TaskManager expectedTM = helper.generateTaskManager(sampleTasks);
        List<Task> expectedList = helper.generateTaskList(editedTask);
        helper.addToModel(model, sampleTasks);

        String expectedMessage = String.format(EditCommand.MESSAGE_SUCCESS, editedTask);
        String validCommand = "edit 1 potato s/now e/1/2/2023 4.56pm";
        assertCommandSuccess(validCommand, expectedMessage, expectedTM, expectedList);
    }

}

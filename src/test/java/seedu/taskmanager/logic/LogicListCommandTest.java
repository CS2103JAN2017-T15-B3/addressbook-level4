package seedu.taskmanager.logic;

import static seedu.taskmanager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import org.junit.Test;

import seedu.taskmanager.logic.commands.ListCommand;
import seedu.taskmanager.model.TaskManager;
import seedu.taskmanager.model.task.ReadOnlyTask;
import seedu.taskmanager.model.task.Task;

public class LogicListCommandTest extends LogicManagerTest {

	@Test
    public void execute_list_invalidTaskType() throws Exception {
        String invalidCommand = "list asdf";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE);
        assertCommandFailure(invalidCommand, expectedMessage);
    }

    @Test
    public void execute_list_showsAllTasks() throws Exception {
        // prepare expectations
        TestDataHelper helper = new TestDataHelper();
        TaskManager expectedAB = helper.generateTaskManager(2);
        List<? extends ReadOnlyTask> expectedList = expectedAB.getTaskList();

        // prepare task manager state
        helper.addToModel(model, 2);

        assertCommandSuccess("list", ListCommand.MESSAGE_SUCCESS, expectedAB, expectedList);
    }

    @Test
    public void execute_list_floating() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Task float1 = helper.generateTaskWithName("potato");
        Task float2 = helper.generateTaskWithName("pineapple");
        Task deadline1 = helper.generateTaskWithDueDate("orange", "now");
        Task deadline2 = helper.generateTaskWithDueDate("qwerty", "tmr");
        Task event1 = helper.generateTaskWithAll("poiuy", "1 jan", "31 jan");
        Task event2 = helper.generateTaskWithAll("esmond", "15 june 2017 12pm", "19 july 2017 1am");

        List<Task> sampleTasks = helper.generateTaskList(float1, float2, deadline1, deadline2, event1, event2);
        TaskManager expectedTM = helper.generateTaskManager(sampleTasks);
        List<Task> expectedList = helper.generateTaskList(float1, float2);
        helper.addToModel(model, sampleTasks);

        String expectedMessage = ListCommand.MESSAGE_SUCCESS + " (floating)";
        String validCommand = "list floating";
        assertCommandSuccess(validCommand, expectedMessage, expectedTM, expectedList);
    }

    @Test
    public void execute_list_deadline() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Task float1 = helper.generateTaskWithName("potato");
        Task float2 = helper.generateTaskWithName("pineapple");
        Task deadline1 = helper.generateTaskWithDueDate("orange", "now");
        Task deadline2 = helper.generateTaskWithDueDate("qwerty", "tmr");
        Task event1 = helper.generateTaskWithAll("poiuy", "1 jan", "31 jan");
        Task event2 = helper.generateTaskWithAll("esmond", "15 june 2017 12pm", "19 july 2017 1am");

        List<Task> sampleTasks = helper.generateTaskList(float1, float2, deadline1, deadline2, event1, event2);
        TaskManager expectedTM = helper.generateTaskManager(sampleTasks);
        List<Task> expectedList = helper.generateTaskList(deadline1, deadline2);
        helper.addToModel(model, sampleTasks);

        String expectedMessage = ListCommand.MESSAGE_SUCCESS + " (deadline)";
        String validCommand = "list deadline";
        assertCommandSuccess(validCommand, expectedMessage, expectedTM, expectedList);
    }

    @Test
    public void execute_list_event() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Task float1 = helper.generateTaskWithName("potato");
        Task float2 = helper.generateTaskWithName("pineapple");
        Task deadline1 = helper.generateTaskWithDueDate("orange", "now");
        Task deadline2 = helper.generateTaskWithDueDate("qwerty", "tmr");
        Task event1 = helper.generateTaskWithAll("poiuy", "1 jan", "31 jan");
        Task event2 = helper.generateTaskWithAll("esmond", "15 june 2017 12pm", "19 july 2017 1am");

        List<Task> sampleTasks = helper.generateTaskList(float1, float2, deadline1, deadline2, event1, event2);
        TaskManager expectedTM = helper.generateTaskManager(sampleTasks);
        List<Task> expectedList = helper.generateTaskList(event1, event2);
        helper.addToModel(model, sampleTasks);

        String expectedMessage = ListCommand.MESSAGE_SUCCESS + " (event)";
        String validCommand = "list event";
        assertCommandSuccess(validCommand, expectedMessage, expectedTM, expectedList);
    }

    @Test
    public void execute_list_date() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Task float1 = helper.generateTaskWithName("potato");
        Task deadline1 = helper.generateTaskWithDueDate("orange", "1 jan");
        Task deadline2 = helper.generateTaskWithDueDate("qwerty", "31 aug");
        Task event1 = helper.generateTaskWithAll("poiuy", "1 jan", "31 jan");
        Task event2 = helper.generateTaskWithAll("esmond", "15 june 2017 12pm", "19 july 2017 1am");

        List<Task> sampleTasks = helper.generateTaskList(float1, deadline1, deadline2, event1, event2);
        TaskManager expectedTM = helper.generateTaskManager(sampleTasks);
        List<Task> expectedList = helper.generateTaskList(deadline1, event1);
        helper.addToModel(model, sampleTasks);

        String expectedMessage = ListCommand.MESSAGE_SUCCESS + " (1 jan)";
        String validCommand = "list 1 jan";
        assertCommandSuccess(validCommand, expectedMessage, expectedTM, expectedList);
    }

    @Test
    public void execute_list_deadlineAndDate() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Task float1 = helper.generateTaskWithName("potato");
        Task deadline1 = helper.generateTaskWithDueDate("orange", "19 july 2017");
        Task deadline2 = helper.generateTaskWithDueDate("qwerty", "19 july");
        Task deadline3 = helper.generateTaskWithDueDate("zxc", "19 nov");
        Task event1 = helper.generateTaskWithAll("poiuy", "1 jan", "31 jan");
        Task event2 = helper.generateTaskWithAll("esmond", "15 june 2017 12pm", "19 july 2017 1am");

        List<Task> sampleTasks = helper.generateTaskList(float1, deadline1, deadline2, deadline3, event1, event2);
        TaskManager expectedTM = helper.generateTaskManager(sampleTasks);
        List<Task> expectedList = helper.generateTaskList(deadline1, deadline2);
        helper.addToModel(model, sampleTasks);

        String expectedMessage = ListCommand.MESSAGE_SUCCESS + " (deadline, 19 july)";
        String validCommand = "list deadline 19 july";
        assertCommandSuccess(validCommand, expectedMessage, expectedTM, expectedList);
    }

    @Test
    public void execute_list_dateAndEvent() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Task float1 = helper.generateTaskWithName("potato");
        Task deadline1 = helper.generateTaskWithDueDate("orange", "19 july 2017");
        Task deadline2 = helper.generateTaskWithDueDate("qwerty", "19 july");
        Task deadline3 = helper.generateTaskWithDueDate("zxc", "19 nov");
        Task event1 = helper.generateTaskWithAll("poiuy", "1 jan", "31 jan");
        Task event2 = helper.generateTaskWithAll("esmond", "15 june 2017 12pm", "19 july 2017 1am");

        List<Task> sampleTasks = helper.generateTaskList(float1, deadline1, deadline2, deadline3, event1, event2);
        TaskManager expectedTM = helper.generateTaskManager(sampleTasks);
        List<Task> expectedList = helper.generateTaskList(event2);
        helper.addToModel(model, sampleTasks);

        String expectedMessage = ListCommand.MESSAGE_SUCCESS + " (event, 19 july)";
        String validCommand = "list 19 july event";
        assertCommandSuccess(validCommand, expectedMessage, expectedTM, expectedList);
    }

}

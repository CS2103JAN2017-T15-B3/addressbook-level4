package seedu.taskmanager.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.taskmanager.model.task.ReadOnlyTask;

public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";
    private static final TaskCardStyle style = TaskCardStyle.getInstance();

    @FXML
    private VBox idContainer;
    @FXML
    private VBox labelContainer;
    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label startDate;
    @FXML
    private Label endDate;
    @FXML
    private Label id;
    @FXML
    private Label taskType;

    @FXML
    private FlowPane tags;

    public TaskCard(ReadOnlyTask task, int displayedIndex) {
        super(FXML);
        initialise(task, displayedIndex);
    }

    private void initialise(ReadOnlyTask task, int displayedIndex) {
        name.setText(task.getName().fullName);
        taskSelector(task);
        doneSelector(task);
        id.setText(displayedIndex + ". ");
        initTags(task);
    }

    private void doneSelector(ReadOnlyTask task) {
        StringBuilder styleString = new StringBuilder();
        String IS_DONE = "Done";
        String NOT_DONE = "Not done";

        if (task.isDone()) {
            styleString.append(style.getCardDoneColour(IS_DONE));

        } else {
            styleString.append(style.getCardDoneColour(NOT_DONE));

        }

        idContainer.setStyle(styleString.toString());
    }

    /**
     * Prints the task in correct format.
     *
     * @param task
     */
    private void taskSelector(ReadOnlyTask task) {
        if (task.isFloating()) {
            deleteLabel(startDate);
            deleteLabel(endDate);
            taskType.setText("Floating");

        } else if (task.isDeadline()) {
            deleteLabel(startDate);
            endDate.setText("Due: " + task.getEndDate().toString());
            taskType.setText("Deadline");

        } else if (task.isEvent()) {
            startDate.setText("Start: " + task.getStartDate().toString());
            endDate.setText("End: " + task.getEndDate().toString());
            taskType.setText("Event");
        } else {
            deleteLabel(startDate);
            deleteLabel(endDate);
            taskType.setText("Error");
        }
    }

    private void deleteLabel(Label label) {
        labelContainer.getChildren().remove(label);
    }

    private void initTags(ReadOnlyTask task) {
        task.getTags().forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}

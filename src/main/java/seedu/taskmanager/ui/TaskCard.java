package seedu.taskmanager.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.taskmanager.model.task.ReadOnlyTask;
import seedu.taskmanager.model.task.TaskUtil;

public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

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
    private FlowPane tags;

    public TaskCard(ReadOnlyTask task, int displayedIndex) {
        super(FXML);
        name.setText(task.getName().fullName);
        taskSelector(task);
        id.setText(displayedIndex + ". ");
        initTags(task);
    }

    /**
     * Prints the task in correct format.
     * @param task
     */
    private void taskSelector(ReadOnlyTask task){
        if(TaskUtil.isFloating(task)){
            startDate.setVisible(false);
            endDate.setVisible(false);

        } else if (TaskUtil.isDeadline(task)){
            startDate.setVisible(false);
            endDate.setText("Due: "+ task.getEndDate().toString());

        } else if (TaskUtil.isEvent(task)){
            startDate.setText("Start: "+ task.getStartDate().toString());
            endDate.setText("End: "+ task.getEndDate().toString());
        }
    }

    private void initTags(ReadOnlyTask task) {
        task.getTags().forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
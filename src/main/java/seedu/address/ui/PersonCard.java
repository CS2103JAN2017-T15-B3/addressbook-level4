package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.TaskUtil;

public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

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

    public PersonCard(ReadOnlyPerson person, int displayedIndex) {
        super(FXML);
        name.setText(person.getName().fullName);
        taskSelector(person);
        id.setText(displayedIndex + ". ");
        initTags(person);
    }
    
    /**
     * Prints the task in correct format.
     * @param person
     */
    private void taskSelector(ReadOnlyPerson person){
        if(TaskUtil.isFloating(person)){
            startDate.setVisible(false);
            endDate.setVisible(false);
            
        } else if (TaskUtil.isDeadline(person)){
            startDate.setVisible(false);
            endDate.setText("Due: "+ person.getEndDate().toString());
        
        } else if (TaskUtil.isEvent(person)){
            startDate.setText("Start: "+ person.getStartDate().toString());
            endDate.setText("End: "+ person.getEndDate().toString());
        }
    }

    private void initTags(ReadOnlyPerson person) {
        person.getTags().forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}

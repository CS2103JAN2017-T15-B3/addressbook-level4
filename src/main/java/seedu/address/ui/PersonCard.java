package seedu.address.ui;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.ReadOnlyFloatingTask;
import seedu.address.logic.parser.ArgumentTokenizer.Prefix;

public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;

    @FXML
    private FlowPane tags;

    public PersonCard(ReadOnlyFloatingTask person, int displayedIndex) {
        super(FXML);
        name.setText(person.getName().fullName);
        id.setText(displayedIndex + ". ");
        initTags(person);
    }

    private void initTags(ReadOnlyFloatingTask person) {
        List<String> a = person.getTags().get(new Prefix("START"));
        List<String> b = person.getTags().get(new Prefix("END"));
        if (!a.isEmpty()) {
            tags.getChildren().add(new Label(a.get(0)));
        }
        if (!b.isEmpty()) {
            tags.getChildren().add(new Label(b.get(0)));
        }
    }
}

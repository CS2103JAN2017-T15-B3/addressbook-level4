
package seedu.taskmanager.storage;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import seedu.taskmanager.commons.exceptions.IllegalValueException;
import seedu.taskmanager.logic.parser.DateTimeUtil;
import seedu.taskmanager.model.tag.Tag;
import seedu.taskmanager.model.tag.UniqueTagList;
import seedu.taskmanager.model.task.Name;
import seedu.taskmanager.model.task.ReadOnlyTask;
import seedu.taskmanager.model.task.Task;
import seedu.taskmanager.model.task.TaskDate;

// @@author A0140538J
/**
 * JAXB-friendly version of the Task.
 */
public class XmlAdaptedTask {

    public static final String NO_DATE = "N/A";

    @XmlElement(
            required = true)
    private String name;
    @XmlElement
    private String startDate;
    @XmlElement
    private String endDate;
    @XmlElement
    private List<XmlAdaptedTag> tagged = new ArrayList<>();
    @XmlElement
    private boolean isDoneStatus;

    /**
     * Constructs an XmlAdaptedTask. This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedTask() {
    }

    /**
     * Converts a given Task into this class for JAXB use.
     * @param source
     *        future changes to this will not affect the created XmlAdaptedTask
     */
    public XmlAdaptedTask(ReadOnlyTask source) {
        name = source.getName().fullName;

        if (source.getStartDate() == null) {
            startDate = NO_DATE;
        } else {
            startDate = source.getStartDate().toString();
        }

        if (source.getEndDate() == null) {
            endDate = NO_DATE;
        } else {
            endDate = source.getEndDate().toString();
        }

        tagged = new ArrayList<>();
        for (Tag tag : source.getTags()) {
            tagged.add(new XmlAdaptedTag(tag));
        }

        isDoneStatus = source.isDone();
    }

    /**
     * Converts this jaxb-friendly adapted task object into the model's Task object.
     * @throws IllegalValueException
     *         if there were any data constraints violated in the adapted task
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> taskTags = new ArrayList<>();
        for (XmlAdaptedTag tag : tagged) {
            taskTags.add(tag.toModelType());
        }
        final Name name = new Name(this.name);
        final TaskDate startDate =
                this.startDate.equals(NO_DATE) ? null : DateTimeUtil.parseStartDateTime(this.startDate);
        final TaskDate endDate = this.endDate.equals(NO_DATE) ? null : DateTimeUtil.parseEndDateTime(this.endDate);
        final UniqueTagList tags = new UniqueTagList(taskTags);
        final boolean isDoneStatus = this.isDoneStatus;
        return new Task(name, startDate, endDate, tags, isDoneStatus);
    }
}

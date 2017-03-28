package seedu.taskmanager.testutil;

import java.util.Optional;

import seedu.taskmanager.model.tag.UniqueTagList;
import seedu.taskmanager.model.task.Name;
import seedu.taskmanager.model.task.ReadOnlyTask;
import seedu.taskmanager.model.task.TaskDate;

/**
 * A mutable task object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

    private Name name;
    private Optional<TaskDate> startDate;
    private Optional<TaskDate> endDate;
    private UniqueTagList tags;

    public TestTask() {
        tags = new UniqueTagList();
    }

    /**
     * Creates a copy of {@code taskToCopy}.
     */
    public TestTask(TestTask taskToCopy) {
        this.name = taskToCopy.getName();
        this.tags = taskToCopy.getTags();
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setTags(UniqueTagList tags) {
        this.tags = tags;
    }

    public void setStartDate(Optional<TaskDate> startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Optional<TaskDate> endDate) {
        this.endDate = endDate;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Optional<TaskDate> getStartDate() {
        return startDate;
    }

    @Override
    public Optional<TaskDate> getEndDate() {
        return endDate;
    }

    @Override
    public UniqueTagList getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return getAsText();
    }

    public String getAddCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("add " + this.getName().fullName + " ");
        this.getTags().asObservableList().stream().forEach(s -> sb.append("t/" + s.tagName + " "));
        return sb.toString();
    }

    @Override
    public boolean isDone() {
        // TODO Auto-generated method stub
        return false;
    }

}

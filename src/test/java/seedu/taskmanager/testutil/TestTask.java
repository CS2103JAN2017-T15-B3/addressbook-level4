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


    public void setStartDate(TaskDate startDate) {
        this.startDate = Optional.of(startDate);
    }

    public void setEndDate(TaskDate endDate) {
        this.endDate = Optional.of(endDate);

    }

    @Override
    public Name getName() {
        return name;
    }

    @Override

    public TaskDate getStartDate() {
        return startDate.get();
    }

    @Override
    public TaskDate getEndDate() {
        return endDate.get();

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

    @Override
    public boolean hasStartDate() {
        return startDate.isPresent();
    }

    @Override
    public boolean hasEndDate() {
        return endDate.isPresent();
    }

    @Override
    public boolean isFloating() {
        return !hasStartDate() && !hasEndDate();
    }

    @Override
    public boolean isDeadline() {
        return !hasStartDate() && hasEndDate();
    }

    @Override
    public boolean isEvent() {
        return hasStartDate() && hasEndDate();
    }
}

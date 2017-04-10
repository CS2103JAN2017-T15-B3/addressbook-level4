//@@author A0140417R

package seedu.taskmanager.commons.events.model;

import seedu.taskmanager.commons.events.BaseEvent;

/** Indicates the a Task in the model is updated */
public class TaskUpdatedEvent extends BaseEvent {

    public final int updatedIndex;

    public TaskUpdatedEvent(int index) {
        this.updatedIndex = index;
    }

    @Override
    public String toString() {
        return "index: " + updatedIndex;
    }
}

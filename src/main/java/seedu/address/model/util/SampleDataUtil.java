package seedu.address.model.util;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.tag.UniqueTagList;
import seedu.address.model.task.Name;
import seedu.address.model.task.FloatingTask;
import seedu.address.model.task.UniquePersonList.DuplicatePersonException;

public class SampleDataUtil {
    public static FloatingTask[] getSamplePersons() {
        try {
            return new FloatingTask[] {
                new FloatingTask(new Name("Alex Yeoh"), new UniqueTagList("friends")),
                new FloatingTask(new Name("Bernice Yu"), new UniqueTagList("colleagues", "friends")),
                new FloatingTask(new Name("Charlotte Oliveiro"), new UniqueTagList("neighbours")),
                new FloatingTask(new Name("David Li"), new UniqueTagList("family")),
                new FloatingTask(new Name("Irfan Ibrahim"), new UniqueTagList("classmates")),
                new FloatingTask(new Name("Roy Balakrishnan"), new UniqueTagList("colleagues"))
            };
        } catch (IllegalValueException e) {
            throw new AssertionError("sample data cannot be invalid", e);
        }
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        try {
            AddressBook sampleAB = new AddressBook();
            for (FloatingTask samplePerson : getSamplePersons()) {
                sampleAB.addPerson(samplePerson);
            }
            return sampleAB;
        } catch (DuplicatePersonException e) {
            throw new AssertionError("sample data cannot contain duplicate persons", e);
        }
    }
}

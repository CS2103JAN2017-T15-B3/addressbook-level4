package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.DateTimeUtil;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.TaskDate;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * JAXB-friendly version of the Person.
 */
public class XmlAdaptedPerson {

    @XmlElement(required = true)
    private String name;
    @XmlElement
    private String startDate;
    @XmlElement
    private String endDate;
    @XmlElement
    private List<XmlAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs an XmlAdaptedPerson.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedPerson() {}


    /**
     * Converts a given Person into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedPerson
     */
    public XmlAdaptedPerson(ReadOnlyPerson source) {
        name = source.getName().fullName;
        startDate = source.getStartDate().toString();
        endDate = source.getEndDate().toString();

        tagged = new ArrayList<>();
        for (Tag tag : source.getTags()) {
            tagged.add(new XmlAdaptedTag(tag));
        }
    }

    /**
     * Converts this jaxb-friendly adapted person object into the model's Person object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (XmlAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        final Name name = new Name(this.name);
        final TaskDate startDate = new TaskDate(DateTimeUtil.parseDateTime(this.startDate));
        final TaskDate endDate = new TaskDate(DateTimeUtil.parseDateTime(this.endDate));
        final UniqueTagList tags = new UniqueTagList(personTags);
        return new Person(name, startDate, endDate, tags);
    }
}

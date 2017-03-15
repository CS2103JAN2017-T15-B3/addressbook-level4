package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;
import seedu.address.model.task.Name;
import seedu.address.model.task.FloatingTask;
import seedu.address.model.task.ReadOnlyFloatingTask;
import seedu.address.logic.parser.ArgumentTokenizer.Prefix;

/**
 * JAXB-friendly version of the Person.
 */
public class XmlAdaptedPerson {

    @XmlElement(required = true)
    private String name;

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
    public XmlAdaptedPerson(ReadOnlyFloatingTask source) {
        name = source.getName().fullName;
        tagged = new ArrayList<>();
        for (Map.Entry<Prefix, List<String>> entry : source.getTags().entrySet()) {
            if (entry.getValue().isEmpty()) {
                continue;
            }
            tagged.add(new XmlAdaptedTag(entry.getKey() + ":" + entry.getValue().get(0)));
        }
    }

    /**
     * Converts this jaxb-friendly adapted person object into the model's Person object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public FloatingTask toModelType() throws IllegalValueException {
        final Map<Prefix, List<String>> tags = new HashMap<Prefix, List<String>>();
        for (XmlAdaptedTag tag : tagged) {
            List <String> list = new ArrayList<String>();
            list.add(tag.tagName.split(":")[1]);
            tags.put(new Prefix(tag.tagName.split(":")[0]), list);
        }
        final Name name = new Name(this.name);
        return new FloatingTask(name, tags);
    }
}

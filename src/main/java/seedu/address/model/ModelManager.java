package seedu.address.model;

import java.util.Set;
import java.util.logging.Logger;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.commons.events.model.AddressBookChangedEvent;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.DateTimeUtil;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.TaskUtil;
import seedu.address.model.person.TaskDate;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.UniquePersonList.PersonNotFoundException;

/**
 * Represents the in-memory model of the task manager data.
 * All changes to any model should be synchronized.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final FilteredList<ReadOnlyPerson> filteredPersons;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, UserPrefs userPrefs) {
        super();
        assert !CollectionUtil.isAnyNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    @Override
    public void resetData(ReadOnlyAddressBook newData) {
        addressBook.resetData(newData);
        indicateAddressBookChanged();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateAddressBookChanged() {
        raise(new AddressBookChangedEvent(addressBook));
    }

    @Override
    public synchronized void deletePerson(ReadOnlyPerson target) throws PersonNotFoundException {
        addressBook.removePerson(target);
        indicateAddressBookChanged();
    }

    @Override
    public synchronized void addPerson(Person person) throws UniquePersonList.DuplicatePersonException {
        addressBook.addPerson(person);
        updateFilteredListToShowAll();
        indicateAddressBookChanged();
    }

    @Override
    public void updatePerson(int filteredPersonListIndex, ReadOnlyPerson editedPerson)
            throws UniquePersonList.DuplicatePersonException {
        assert editedPerson != null;

        int addressBookIndex = filteredPersons.getSourceIndex(filteredPersonListIndex);
        addressBook.updatePerson(addressBookIndex, editedPerson);
        indicateAddressBookChanged();
    }

    //=========== Filtered Person List Accessors =============================================================

    @Override
    public UnmodifiableObservableList<ReadOnlyPerson> getFilteredPersonList() {
        return new UnmodifiableObservableList<>(filteredPersons);
    }

    @Override
    public void updateFilteredListToShowAll() {
        filteredPersons.setPredicate(null);
    }

    @Override
    public void updateFilteredTaskListByTaskName(Set<String> keywords) {
        updateFilteredPersonList(new PredicateExpression(new NameQualifier(keywords)));
    }

    public void updateFilteredTaskListByTaskType(String taskType) {
    	updateFilteredPersonList(new PredicateExpression(new TypeQualifier(taskType)));
    }
    
    private void updateFilteredPersonList(Expression expression) {
        filteredPersons.setPredicate(expression::satisfies);
    }

    //========== Inner classes/interfaces used for filtering =================================================

    interface Expression {
        boolean satisfies(ReadOnlyPerson person);
        String toString();
    }

    private class PredicateExpression implements Expression {

        private final Qualifier qualifier;

        PredicateExpression(Qualifier qualifier) {
            this.qualifier = qualifier;
        }

        @Override
        public boolean satisfies(ReadOnlyPerson person) {
            return qualifier.run(person);
        }

        @Override
        public String toString() {
            return qualifier.toString();
        }
    }

    interface Qualifier {
        boolean run(ReadOnlyPerson person);
        String toString();
    }

    private class NameQualifier implements Qualifier {
        private Set<String> nameKeyWords;

        NameQualifier(Set<String> nameKeyWords) {
            this.nameKeyWords = nameKeyWords;
        }

        @Override
        public boolean run(ReadOnlyPerson person) {
            return nameKeyWords.stream()
                    .filter(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword))
                    .findAny()
                    .isPresent();
        }

        @Override
        public String toString() {
            return "name=" + String.join(", ", nameKeyWords);
        }
    }

    private class TypeQualifier implements Qualifier {
    	private String taskType;
    	
    	TypeQualifier(String taskType) {
    		this.taskType = taskType;
    	}
    	
    	@Override
    	public boolean run(ReadOnlyPerson task) {
    		switch (taskType) {
    			case "floating":
    				return TaskUtil.isFloating(task);
    			case "deadline":
    				return TaskUtil.isDeadline(task);
    			case "event":
    				return TaskUtil.isEvent(task);
    			// for parsing date
    			default:
    				TaskDate date = new TaskDate(DateTimeUtil.parseDateTime(taskType));
    				return task.getStartDate().getOnlyDate().equals(date.getOnlyDate()) ||
    						task.getEndDate().getOnlyDate().equals(date.getOnlyDate());
    		}
    	}
    }
}

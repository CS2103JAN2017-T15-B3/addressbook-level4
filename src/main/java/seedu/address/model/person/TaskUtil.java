package seedu.address.model.person;

public class TaskUtil {
    private static boolean hasStart(ReadOnlyPerson task) {
    	return !task.getStartDate().equals(new DummyStartTaskDate());
    }
    
    private static boolean hasEnd(ReadOnlyPerson task) {
    	return !task.getEndDate().equals(new DummyEndTaskDate());
    }
    
    public static boolean isFloating(ReadOnlyPerson task){
        return (!hasStart(task) && !hasEnd(task));
    }
    
    public static boolean isDeadline(ReadOnlyPerson task){
        return (hasStart(task) && !hasEnd(task) || !hasStart(task) && hasEnd(task));
    }
    
    public static boolean isEvent(ReadOnlyPerson task){
        return (hasStart(task) && hasEnd(task));
    }
}

// @@author A0140417R

package seedu.taskmanager.logic.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import seedu.taskmanager.commons.exceptions.IllegalValueException;

public class DateMarkerParserTest {

    @Test
    public void testFormat_containsFromAndTo_assertEqual() {
        String test = "apples from today to tomorrow";
        String expected = "apples s/ today e/ tomorrow";
        try {
            assertEquals(DateMarkerParser.replaceMarkersWithPrefix(test), expected);
        } catch (IllegalValueException e) {
            // Not possible with the given test string
            return;
        }
    }

    @Test
    public void testFormat_containsFrom_assertEqual() {
        String test = "apples from today";
        String expected = "apples s/ today";
        try {
            assertEquals(DateMarkerParser.replaceMarkersWithPrefix(test), expected);
        } catch (IllegalValueException e) {
            // Not possible with the given test string
            return;
        }
    }

    @Test
    public void testFormat_containTo_assertEqual() {
        String test = "apples to tomorrow";
        String expected = "apples e/ tomorrow";
        try {
            assertEquals(DateMarkerParser.replaceMarkersWithPrefix(test), expected);
        } catch (IllegalValueException e) {
            // Not possible with the given test string
            return;
        }
    }

    @Test
    public void testFormat_multipleWordsContainTo_assertEqual() {
        String test = "apples bananas to tomorrow";
        String expected = "apples bananas e/ tomorrow";
        try {
            assertEquals(DateMarkerParser.replaceMarkersWithPrefix(test), expected);
        } catch (IllegalValueException e) {
            // Not possible with the given test string
            return;
        }
    }

    @Test
    public void testFormat_containsBy_assertEqual() {
        String test = "apples by tomorrow";
        String expected = "apples e/ tomorrow";
        try {
            assertEquals(DateMarkerParser.replaceMarkersWithPrefix(test), expected);
        } catch (IllegalValueException e) {
            // Not possible with the given test string
            return;
        }
    }

    @Test(
            expected = IllegalValueException.class)
    public void testFormat_containsRepeatedPrefix_assertException() throws IllegalValueException {
        String test = "apples to today by tomorrow";
        DateMarkerParser.replaceMarkersWithPrefix(test);
    }

    @Test
    public void testFormat_containsFromWithoutDate_assertEqual() {
        String test = "apples from richard";
        String expected = test;
        try {
            assertEquals(DateMarkerParser.replaceMarkersWithPrefix(test), expected);
        } catch (IllegalValueException e) {
            // Not possible with the given test string
            return;
        }
    }

    @Test
    public void testFormat_containsToWithoutDate_assertEqual() {
        String test = "apples to richard";
        String expected = test;
        try {
            assertEquals(DateMarkerParser.replaceMarkersWithPrefix(test), expected);
        } catch (IllegalValueException e) {
            // Not possible with the given test string
            return;
        }
    }
}

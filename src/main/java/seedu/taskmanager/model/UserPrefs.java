
package seedu.taskmanager.model;

import java.util.Objects;

import seedu.taskmanager.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs {

    public GuiSettings guiSettings;

    public String notificationSetting;

    public GuiSettings getGuiSettings() {
        return guiSettings == null ? new GuiSettings() : guiSettings;
    }

    public void updateLastUsedGuiSetting(GuiSettings guiSettings) {
        this.guiSettings = guiSettings;
    }

    public String getNotificationSetting() {
        return notificationSetting;
    }

    public UserPrefs() {
        this.setGuiSetting(500, 500, 0, 0);
        setNotificationSettings(TaskNotifierManager.notificationSetting);
    }

    public void setGuiSetting(double width, double height, int x, int y) {
        guiSettings = new GuiSettings(width, height, x, y);
    }

    public void setNotificationSettings(String newSetting) {
        this.notificationSetting = newSetting;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { // this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return Objects.equals(guiSettings, o.guiSettings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings);
    }

    @Override
    public String toString() {
        return guiSettings.toString();
    }

}

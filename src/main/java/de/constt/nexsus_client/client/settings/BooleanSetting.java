package de.constt.nexsus_client.client.settings;

import de.constt.nexsus_client.client.interfaces.SettingInterface;

public class BooleanSetting implements SettingInterface<Boolean> {
    private final String name;
    private boolean value;

    public BooleanSetting(String name, boolean defaultValue) {
        this.name = name;
        this.value = defaultValue;
    }

    public String getName() {
        return name;
    }

    public Boolean get() {
        return value;
    }

    public void set(Boolean value) {
        this.value = value;
    }
}

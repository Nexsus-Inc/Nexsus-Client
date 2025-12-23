package de.constt.nexsus_client.client.settings;

public class NumberSetting {
    private int max;
    private int min;
    private int defaultValue;
    private int value;

    public NumberSetting(int max, int min, int defaultValue) {
        this.value = defaultValue;
    }

    public Integer getMax() {
        return max;
    }

    public Integer getMin() { return min; }

    public Integer getValue() { return value; }

    public Integer getDefaultValue() { return defaultValue; }

    public void set(Integer value) {
        this.value = value;
    }
}

package de.constt.nexsus_client.client.settings;

public class FloatSetting {
    private float max;
    private float min;
    private float defaultValue;
    private float value;

    public FloatSetting(float max, float min, float defaultValue) {
        this.value = defaultValue;
    }

    public float getMax() {
        return max;
    }

    public float getMin() { return min; }

    public float getValue() { return value; }

    public float getDefaultValue() { return defaultValue; }

    public void set(float value) {
        this.value = value;
    }
}

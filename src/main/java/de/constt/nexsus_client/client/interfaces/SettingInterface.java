package de.constt.nexsus_client.client.interfaces;

public interface SettingInterface<T> {
    String getName();
    T get();
    void set(T value);
}

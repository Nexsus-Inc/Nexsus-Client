package de.constt.nexsus_client.client.roots.implementations;

import de.constt.nexsus_client.client.helperFunctions.ModuleAnnotationHelperFunction;
import de.constt.nexsus_client.client.interfaces.SettingInterface;
import net.minecraft.network.packet.Packet;

import java.util.ArrayList;
import java.util.List;

public abstract class ModuleImplementation {
    protected boolean enabled = false;
    public int keyBindingCode;
    protected final List<SettingInterface<?>> settings = new ArrayList<>();

    /**
     * Toggle the module
     *
     */
    public void toggle() {
        this.enabled = !this.enabled;
    }

    /**
     * Get the keybinding code of the module
     * @return Keybinding Code
     */
    public int getKeybindingCode() {
        return keyBindingCode;
    }

    /**
     * Runs when settings are changed
     */
    public void setSettings() { }

    /**
     * Gets all settings of the module
     * @return Settings
     */
    public List<SettingInterface<?>> getSettings() {
        return settings;
    }

    /**
     * Registers a setting
     * @param setting Setting
     * @return Setting
     * @param <T> Setting
     */
    protected <T extends SettingInterface<?>> T register(T setting) {
        settings.add(setting);
        return setting;
    }

    /**
     * Return the name of the module
     * @return the name of the module
     */
    public String getTranslatableText() {
        return ModuleAnnotationHelperFunction.getName(this.getClass());
    };

    /**
     * Check the status of the module
     * @return  true if the module is enabled, else false
     */
    public boolean getEnabledStatus() {
        return enabled;
    }

    /**
     * Is called at {@code START_CLIENT_TICK}
     */
    public void tick() { }

    /**
     * Is called at {@code END_CLIENT_TICK}
     */
    public void postTick() { }

    /**
     * Is called when the module gets enabled
     */
    public void onEnable() { }

    /**
     * Is called when the module gets disabled
     */
    public void onDisable() { }

    /**
     * Modify minecraft packets from the module
     * @param packet Packet
     * @return False
     */
    public boolean modifyPacket(Packet<?> packet) {
        return false;
    };
}

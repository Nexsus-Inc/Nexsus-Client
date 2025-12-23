package de.constt.nexsus_client.client.roots.commands.modules;

import de.constt.nexsus_client.client.annotations.CommandAnnotation;
import de.constt.nexsus_client.client.helperFunctions.ChatHelperFunction;
import de.constt.nexsus_client.client.roots.implementations.CommandImplementation;
import de.constt.nexsus_client.client.roots.modules.ModuleManager;

@CommandAnnotation(
        name = "Set Setting",
        description = "Change module settings",
        command = "setsetting"
)

public class SetSettingComand extends CommandImplementation {
    @Override
    public void executeCommand(String[] parts) {
        if (parts.length < 2) {
            ChatHelperFunction.sendCSMessageNeutral("Usage: *setsetting <module> <setting> <value>");
        } else {
            if (parts.length < 3) {
                ChatHelperFunction.sendCSMessageNeutral("Usage: *setsetting <module> <setting> <value>");
            }

            String moduleArg = parts[1].toLowerCase();
            String settingArg = parts[2].toUpperCase();
            String valueArg = parts[3].toUpperCase();

            ModuleManager.getModules().forEach(module -> {
                // Check if module is the name of the module in arg (moduleArg)
                // Get setting in module (settingArg)
                // Set Setting to new value
                // Show that new value is set and what the old value was
            });

        }
    }
}

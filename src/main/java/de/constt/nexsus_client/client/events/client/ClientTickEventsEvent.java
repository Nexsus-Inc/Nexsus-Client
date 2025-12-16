package de.constt.nexsus_client.client.events.client;

import de.constt.nexsus_client.client.config.NexsusConfigData;
import de.constt.nexsus_client.client.roots.modules.ModuleManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class ClientTickEventsEvent {
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            String clientVersion = NexsusConfigData.getConfigValue("clientVersion");
            if (client.getWindow() != null) {
                client.getWindow().setTitle("Nexsus Client "+clientVersion+" | MC 1.21.1 Fabric");
            }
        });

        ClientTickEvents.START_CLIENT_TICK.register(client ->
                ModuleManager.getModules().forEach(module -> {
                    if (module.getEnabledStatus())
                        module.tick();
                })
        );

        ClientTickEvents.END_CLIENT_TICK.register(client ->
                ModuleManager.getModules().forEach(module -> {
                    if (module.getEnabledStatus())
                        module.postTick();
                })
        );
    }
}

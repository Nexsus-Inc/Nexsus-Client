package de.constt.nexsus_client.client;

import com.jagrosh.discordipc.exceptions.NoDiscordClientException;
import de.constt.nexsus_client.client.config.NexsusConfigData;
import de.constt.nexsus_client.client.discordRPC.discordIPCCore;
import de.constt.nexsus_client.client.events.chat.ClientReciveMessageEvent;
import de.constt.nexsus_client.client.events.client.ClientTickEventsEvent;
import de.constt.nexsus_client.client.events.hud.HudRenderCallbackEvent;
import de.constt.nexsus_client.client.events.player.ClientPlayerConnectionEvents;
import de.constt.nexsus_client.client.helperFunctions.chatHelperFunction;

import de.constt.nexsus_client.client.roots.modules.ModuleManager;
import de.constt.nexsus_client.client.roots.modules.player.NoHungerModule;
import de.constt.nexsus_client.client.roots.modules.world.NoFallModule;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Nexsus_clientClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("nexsus_client");
    public static final String MOD_ID = "nexsus_client";

    @Override
    public void onInitializeClient() {
        // CONFIG: Load
        NexsusConfigData.init();

        // CHAT HELPER FUNCTION: Set Prefix
        chatHelperFunction.setPrefix("☐ Nexsus");

        // DISCORD IPC: Start
        try {
            discordIPCCore.start();
        } catch (NoDiscordClientException e) {
            throw new RuntimeException(e);
        }

        // EVENTS REGISTRATION
        ClientReciveMessageEvent.register();
        HudRenderCallbackEvent.register();
        ClientPlayerConnectionEvents.register();
        ClientTickEventsEvent.register();

        // ModuleManager
        ModuleManager.init();

        // Temporarily toggle Modules
        ModuleManager.tempToggleModules();
    }
}

package de.constt.nexsus_client.client;

import com.jagrosh.discordipc.exceptions.NoDiscordClientException;
import de.constt.nexsus_client.client.config.NexsusConfigData;
import de.constt.nexsus_client.client.discordRPC.discordIPCCore;
import de.constt.nexsus_client.client.events.chat.ClientReciveMessageEvent;
import de.constt.nexsus_client.client.events.client.ClientTickEventsEvent;
import de.constt.nexsus_client.client.events.hud.HudRenderCallbackEvent;
import de.constt.nexsus_client.client.events.player.ClientPlayerConnectionEvents;
import de.constt.nexsus_client.client.events.render.ScreenRenderCallbackEvent;
import de.constt.nexsus_client.client.helperFunctions.chatHelperFunction;

import de.constt.nexsus_client.client.roots.gui.ModulesScreen;
import de.constt.nexsus_client.client.roots.modules.ModuleManager;
import de.constt.nexsus_client.client.roots.modules.player.NoHungerModule;
import de.constt.nexsus_client.client.roots.modules.world.NoFallModule;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Nexsus_clientClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("nexsus_client");
    public static final String MOD_ID = "nexsus_client";

    private static final String CATEGORY = "key.categories.nexsus_client";
    private static KeyBinding menuKey;

    @Override
    public void onInitializeClient() {
        // CONFIG: Load
        NexsusConfigData.init();

        // CHAT HELPER FUNCTION: Set Prefix
        chatHelperFunction.setPrefix("Nexsus");

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
        ScreenRenderCallbackEvent.register();

        // ModuleManager
        ModuleManager.init();

        // Temporarily toggle Modules
        ModuleManager.tempToggleModules();

        menuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nexsus_client.menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                CATEGORY
        ));
        net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (menuKey.wasPressed()) {
                client.setScreen(new ModulesScreen("Nexsus Client Modules Screen"));
            }
        });
    }
}

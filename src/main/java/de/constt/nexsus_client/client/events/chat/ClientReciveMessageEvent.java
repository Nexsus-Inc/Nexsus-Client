package de.constt.nexsus_client.client.events.chat;

import de.constt.nexsus_client.client.helperFunctions.ChatHelperFunction;
import de.constt.nexsus_client.client.helperFunctions.CommandAnnotationHelper;
import de.constt.nexsus_client.client.roots.commands.CommandManager;
import de.constt.nexsus_client.client.roots.modules.ModuleManager;
import de.constt.nexsus_client.client.roots.modules.misc.DebuggerModule;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

import java.util.Arrays;
import java.util.Objects;

public class ClientReciveMessageEvent {

    public static void register() {
        ClientSendMessageEvents.ALLOW_CHAT.register((message) -> {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;

            assert player != null;

            if (!message.startsWith("/") && message.startsWith("*")) {
                String[] parts = message.split(" ");
                String command = parts[0].substring(1);

                if(ModuleManager.isEnabled(DebuggerModule.class)) {
                    ChatHelperFunction.sendCSMessageWarning("Command: "+command);
                    ChatHelperFunction.sendCSMessageWarning("Parts: "+ Arrays.toString(parts));
                }

                CommandManager.getCommands().forEach(commandFL -> {
                    if(command.equals(CommandAnnotationHelper.getCommand(commandFL.getClass()))) {
                        Objects.requireNonNull(CommandManager.getCommand(commandFL.getClass())).executeCommand(parts);
                    }
                });
                return false;
            }

            return true; // allow normal messages
        });
    }
}
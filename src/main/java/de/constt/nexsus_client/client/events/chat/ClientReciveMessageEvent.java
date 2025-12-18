package de.constt.nexsus_client.client.events.chat;

import de.constt.nexsus_client.client.helperFunctions.chatHelperFunction;
import de.constt.nexsus_client.client.helperFunctions.moduleAnnotationHelperFunction;
import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;
import de.constt.nexsus_client.client.roots.modules.ModuleManager;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;

public class ClientReciveMessageEvent {

    public static void register() {
        ClientSendMessageEvents.ALLOW_CHAT.register((message) -> {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;

            assert player != null;

            if (!message.startsWith("/") && message.startsWith("*")) {
                String[] parts = message.split(" ");
                String command = parts[0];

                if (command.equals("*")) {
                    chatHelperFunction.sendCSMessageNeutral("Please specify a command! *help for commands");
                } else if (command.equals("*bind")) {
                    if (parts.length < 2) {
                        chatHelperFunction.sendCSMessageNeutral("Usage: *bind <module> <key>");
                    } else {
                        if (parts.length < 3) {
                            chatHelperFunction.sendCSMessageNeutral("Usage: *bind <module> <key>");
                            return false;
                        }

                        String moduleArg = parts[1];
                        String keybindArg = parts[2].toUpperCase();

                        ModuleManager.getModules().forEach(module -> {
                            if (Objects.equals(moduleAnnotationHelperFunction.getName(module.getClass()), moduleArg)) {

                                int keyCode = getKeyCode(keybindArg);

                                ModuleManager.setBind(module, keyCode);

                                chatHelperFunction.sendCSMessageNeutral("Bound " + moduleArg + " to " + keybindArg);

                                chatHelperFunction.sendCSMessageWarning("Bind: "+module.getKeybindingCode());
                            }
                        });

                    }
                } else {
                    chatHelperFunction.sendCSMessageNeutral("Help message");
                }
                return false; // cancel sending the original message
            }

            return true; // allow normal messages
        });
    }

    private static int getKeyCode(String keybindArg) {
        int keyCode = 0;

        // simple A-Z binding
        if (keybindArg.length() == 1) {
            char c = keybindArg.charAt(0);
            if (c >= 'A' && c <= 'Z') keyCode = GLFW.GLFW_KEY_A + (c - 'A');
            else if (c >= '0' && c <= '9') keyCode = GLFW.GLFW_KEY_0 + (c - '0');
        } else if (keybindArg.equals("SPACE")) keyCode = GLFW.GLFW_KEY_SPACE;
        else if (keybindArg.equals("SHIFT")) keyCode = GLFW.GLFW_KEY_LEFT_SHIFT;
        return keyCode;
    }
}
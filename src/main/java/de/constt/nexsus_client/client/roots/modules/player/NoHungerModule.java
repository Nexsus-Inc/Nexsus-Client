package de.constt.nexsus_client.client.roots.modules.player;

import de.constt.nexsus_client.client.Nexsus_clientClient;
import de.constt.nexsus_client.client.annotations.InfoAnnotation;
import de.constt.nexsus_client.client.helperFunctions.chatHelperFunction;
import de.constt.nexsus_client.client.roots.implementations.CategoryImplementation;
import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

@InfoAnnotation(
        name = "No-Hunger",
        description = "Reduces the hunger taken by e.g. sprinting (Does not remove hunger)",
        category = CategoryImplementation.Categories.PLAYER
)
public class NoHungerModule extends ModuleImplementation {
    @Override
    public boolean modifyPacket(Packet<?> packet) {
        if (!(packet instanceof ClientCommandC2SPacket commandPacket)) return false;

        return commandPacket.getMode() == ClientCommandC2SPacket.Mode.START_SPRINTING;
    }
}

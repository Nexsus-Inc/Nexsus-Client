package de.constt.nexsus_client.client.roots.modules.movement;

import de.constt.nexsus_client.client.annotations.ModuleInfoAnnotation;
import de.constt.nexsus_client.client.roots.implementations.CategoryImplementation;
import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

@ModuleInfoAnnotation(
        name = "GUI Move",
        description = "Lets you move while being in guis",
        category = CategoryImplementation.Categories.MOVEMENT,
        internalModuleName = "guimove"
)
public class GUIMoveModule extends ModuleImplementation {

    @Override
    public void onEnable() {
        ClientTickEvents.END_CLIENT_TICK.register(this::tick);
    }

    private void tick(MinecraftClient client) {
        if (client.player == null) return;
        if (client.currentScreen == null) return;

        float forward = 0f;
        float sideways = 0f;

        if (client.options.forwardKey.isPressed()) forward += 1f;
        if (client.options.backKey.isPressed()) forward -= 1f;
        if (client.options.leftKey.isPressed()) sideways += 1f;
        if (client.options.rightKey.isPressed()) sideways -= 1f;

        client.player.input.movementForward = forward;
        client.player.input.movementSideways = sideways;

        client.player.input.jumping = client.options.jumpKey.isPressed();
        client.player.input.sneaking = client.options.sneakKey.isPressed();

        if (client.options.sprintKey.isPressed()) {
            client.player.setSprinting(true);
        }
    }
}

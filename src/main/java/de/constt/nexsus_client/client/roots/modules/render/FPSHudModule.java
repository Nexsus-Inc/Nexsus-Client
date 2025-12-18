package de.constt.nexsus_client.client.roots.modules.render;


import de.constt.nexsus_client.client.annotations.InfoAnnotation;
import de.constt.nexsus_client.client.roots.implementations.CategoryImplementation;
import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;

@InfoAnnotation(
        name = "FPS-(Hud)",
        description = "Shows your FPS in the HUD",
        category = CategoryImplementation.Categories.RENDER
)
public class FPSHudModule extends ModuleImplementation {
}

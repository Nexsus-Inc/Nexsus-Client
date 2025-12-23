package de.constt.nexsus_client.client.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import de.constt.nexsus_client.client.roots.modules.render.StorageESPModule;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.PalettedContainer;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    private static final Set<Class<? extends BlockEntity>> STORAGE = Set.of(
            ChestBlockEntity.class,
            TrappedChestBlockEntity.class,
            BarrelBlockEntity.class,
            ShulkerBoxBlockEntity.class,
            EnderChestBlockEntity.class
    );

    @Unique
    private void renderESP(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            Camera camera,
            float tickDelta,
            CallbackInfo ci
    ) {
        StorageESPModule module = StorageESPModule.getInstance();
        if (module == null || !module.getEnabledStatus()) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) return;

        Vec3d camPos = camera.getPos();

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.setShader(GameRenderer::getPositionColorProgram);

        Tessellator.getInstance().begin(VertexFormat.DrawMode.LINES, VertexFormats.POSITION_COLOR);

        ClientWorld world = MinecraftClient.getInstance().world;

        assert world != null;
        for (WorldChunk chunk : world.getChunkManager()) {
            ChunkSection[] sections = chunk.getSectionArray();
            int sectionY = chunk.getBottomSectionCoord();

            for (ChunkSection section : sections) {
                if (section == null || section.isEmpty()) {
                    sectionY++;
                    continue;
                }

                PalettedContainer<BlockState> states = section.getBlockStateContainer();

                for (int i = 0; i < 4096; i++) {
                    int x = i & 15;
                    int z = (i >> 4) & 15;
                    int y = (i >> 8) & 15;

                    BlockState state = states.get(i);
                    int worldY = (sectionY << 4) + y;
                    int worldX = (chunk.getPos().x << 4) + x;
                    int worldZ = (chunk.getPos().z << 4) + z;
                    BlockPos pos = new BlockPos(worldX, worldY, worldZ);
                }

                sectionY++;
            }
        }

        for (BlockEntity be : client.world.) {

            if (!STORAGE.contains(be.getClass())) continue;

            Box box = new Box(be.getPos()).expand(0.002).offset(
                    -camPos.x,
                    -camPos.y,
                    -camPos.z
            );

            WorldRenderer.drawBox(
                    matrices,
                    buffer,
                    box,
                    0.2f,
                    0.8f,
                    1.0f,
                    1.0f
            );
        }

        BufferRenderer.drawWithGlobalProgram(buffer.end());


        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
    }
}

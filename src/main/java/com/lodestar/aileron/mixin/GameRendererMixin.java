package com.lodestar.aileron.mixin;

import com.lodestar.aileron.accessor.AileronGameRenderer;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GameRenderer.class)
public class GameRendererMixin implements AileronGameRenderer {

    @Shadow @Final private Camera mainCamera;

    @Override
    public Camera aileron$getCamera() {
        return mainCamera;
    }
}

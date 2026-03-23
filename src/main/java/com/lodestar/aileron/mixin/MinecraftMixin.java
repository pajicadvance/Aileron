package com.lodestar.aileron.mixin;

import com.lodestar.aileron.Aileron;
import com.lodestar.aileron.accessor.AileronCamera;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

	@Shadow @Final public GameRenderer gameRenderer;
	@Unique float previousEMA = 0.0f;
	@Unique float EMA = 0.0f;

    @SuppressWarnings("DataFlowIssue")
    @Inject(method = "tick", at = @At("TAIL"))
	public void tick(CallbackInfo ci) {
		Camera camera = gameRenderer.getMainCamera();
		AileronCamera ema = ((AileronCamera) camera);

		float curYaw = camera.entity() != null ? camera.entity().getYRot() : 0;

		previousEMA = EMA;
		EMA = (float) Mth.lerp(Aileron.CONFIG.cameraSettings.cameraRollSpeed.get(), EMA, curYaw);

		ema.aileron$setPreviousEMAValue(previousEMA);
		ema.aileron$setEMAValue(EMA);
	}

}

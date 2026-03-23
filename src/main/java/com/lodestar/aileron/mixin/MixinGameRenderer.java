package com.lodestar.aileron.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.lodestar.aileron.CompatFlags;
import com.lodestar.aileron.Aileron;
import com.lodestar.aileron.accessor.AileronCamera;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {

	@Shadow @Final private Camera mainCamera;
	@Unique private float smoothDeltaMovementSpeed = 0.0f;

	@Inject(
            method = "renderLevel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/GameRenderer;bobHurt(Lnet/minecraft/client/renderer/state/level/CameraRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;)V"
            )
    )
	public void renderLevel(DeltaTracker deltaTracker, CallbackInfo ci, @Local(name = "bobStack") PoseStack bobStack) {
		Minecraft minecraft = Minecraft.getInstance();
		LocalPlayer player = minecraft.player;
		if (player != null && player.isFallFlying() && !(CompatFlags.CAMERA_OVERHAUL_LOADED || CompatFlags.BARREL_ROLL_LOADED)) {
			float partial = mainCamera.getCameraEntityPartialTicks(deltaTracker);

			float roll = ((AileronCamera) mainCamera).aileron$getSmoothedEMADifference(player, partial) * 0.225f;
			float deltaMovementSpeed = (float) player.getDeltaMovement().length();
			smoothDeltaMovementSpeed = Mth.lerp(0.2f, smoothDeltaMovementSpeed, deltaMovementSpeed);
			float rotation = (float) (roll * smoothDeltaMovementSpeed * Aileron.CONFIG.cameraSettings.cameraRollScale.get());

			if (Aileron.CONFIG.cameraSettings.doCameraRoll.get())
				bobStack.mulPose(Axis.ZP.rotationDegrees(rotation));
		}
	}
}

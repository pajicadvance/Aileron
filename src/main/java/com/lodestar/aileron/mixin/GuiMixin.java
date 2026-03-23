package com.lodestar.aileron.mixin;

import com.lodestar.aileron.AileronGuiRender;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {

	@ModifyArg(
            method = "extractItemHotbar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V",
                    ordinal = 4
            ),
            index = 2
    )
	public int moveAttackIndicatorBackground(int x) {
        return AileronGuiRender.moveAttackIndicator(x);
    }

    @ModifyArg(
            method = "extractItemHotbar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIIIIII)V"
            ),
            index = 6
    )
    public int moveAttackIndicatorProgress(int x) {
        return AileronGuiRender.moveAttackIndicator(x);
    }

	@Inject(
            method = "extractItemHotbar",
            at = @At(value = "TAIL")
    )
	public void renderSmokeStackBar(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
		AileronGuiRender.renderSmokeStackBar(graphics);
	}
}

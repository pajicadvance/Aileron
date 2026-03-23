package com.lodestar.aileron;

import com.lodestar.aileron.accessor.AileronPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.HumanoidArm;

public class AileronGuiRender {

    private static final Identifier TEXTURE_EMPTY = Aileron.withModNamespace("smokestack_empty");
    private static final Identifier TEXTURE_FULL = Aileron.withModNamespace("smokestack_full");

    public static int moveAttackIndicator(int x) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null && Aileron.canChargeSmokeStack(player)) {
            return player.getMainArm() == HumanoidArm.LEFT ? x - 10 : x + 6;
        }
        return x;
    }

    public static void renderSmokeStackBar(GuiGraphicsExtractor graphics) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null || !Aileron.canChargeSmokeStack(player)) return;

        int smokeStockLevel = ((AileronPlayer) player).aileron$getSmokestackCapacity();
        boolean left = player.getMainArm() == HumanoidArm.LEFT;

        int screenX = (graphics.guiWidth() / 2);
        if (left) screenX -= 102;
        else screenX += 92;

        int screenY = graphics.guiHeight() - 10;

        int smokeStackCharges = ((AileronPlayer) player).aileron$getSmokestackCharges();
        for (int spriteIndex = 0; spriteIndex < smokeStockLevel; spriteIndex++) {
            Identifier texture;
            int xPos = spriteIndex / 3;
            int yPos = spriteIndex % 3;
            if (smokeStackCharges > spriteIndex) texture = TEXTURE_FULL;
            else texture = TEXTURE_EMPTY;
            int spriteX = screenX + ((xPos * 8) * (left ? -1 : 1));
            int spriteY = screenY - (yPos * 9);
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, texture, spriteX, spriteY, 9, 9);
        }
    }
}

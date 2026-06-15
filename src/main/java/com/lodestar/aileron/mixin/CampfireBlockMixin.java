package com.lodestar.aileron.mixin;

import com.lodestar.aileron.Aileron;
import com.lodestar.aileron.accessor.AileronPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.CampfireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CampfireBlock.class)
public class CampfireBlockMixin {

	@SuppressWarnings("deprecation")
    @Redirect(
            method = "entityInside",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)V"
            )
    )
	public void hurt(Entity instance, DamageSource source, float damage) {
		if (!(
                instance.isCrouching() &&
                ((instance instanceof Player && Aileron.isElytra(Aileron.getElytra((Player) instance)))) ||
                (instance instanceof Player && (((AileronPlayer) instance).aileron$getCampfireDamageIFrames() > 0))
        ))
            instance.hurt(source, damage);
	}
}

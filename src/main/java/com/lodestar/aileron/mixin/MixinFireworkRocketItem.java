package com.lodestar.aileron.mixin;

import com.lodestar.aileron.FireworkUseBehavior;
import com.lodestar.aileron.Aileron;
import com.lodestar.aileron.accessor.AileronPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FireworkRocketItem.class)
public class MixinFireworkRocketItem {

	@Inject(
            method = "use",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/projectile/FireworkRocketEntity;<init>(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;)V"
            ),
            cancellable = true
    )
	public void use(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
		if (Aileron.CONFIG.generalChanges.fireworkUseBehavior.get() == FireworkUseBehavior.NORMAL) return;
		if (Aileron.CONFIG.generalChanges.fireworkUseBehavior.get() == FireworkUseBehavior.DISABLE) {
			cir.setReturnValue(InteractionResult.PASS);
			return;
		}

		ItemStack stack = player.getItemInHand(hand);
		if (!player.getAbilities().instabuild) stack.shrink(1);

		((AileronPlayer) player).aileron$setSmokeTrailTicks(100);
		player.getCooldowns().addCooldown(stack, 100);
		player.awardStat(Stats.ITEM_USED.get((FireworkRocketItem) (Object) this));
		cir.setReturnValue(InteractionResult.SUCCESS);
	}
}

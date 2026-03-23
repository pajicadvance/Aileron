package com.lodestar.aileron.mixin;

import com.lodestar.aileron.accessor.AileronCamera;
import net.minecraft.client.Camera;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Camera.class)
public abstract class CameraMixin implements AileronCamera {

	@Unique double previousEMAValue = 0.0;
	@Unique double EMAValue = 0.0;

	@Shadow
	public abstract float yRot();

	@Override
	public double aileron$getPreviousEMAValue() {
		return previousEMAValue;
	}

	@Override
	public void aileron$setPreviousEMAValue(float previousEMA) {
		previousEMAValue = previousEMA;
	}

	@Override
	public double aileron$getEMAValue() {
		return EMAValue;
	}

	@Override
	public void aileron$setEMAValue(float EMA) {
		EMAValue = EMA;
	}

	@Override
	public float aileron$getSmoothedEMADifference(Entity entity, float partial) {
		return entity != null ? entity.getYRot() - (float)Mth.lerp(partial, previousEMAValue, EMAValue) : 0.0f;
	}
}

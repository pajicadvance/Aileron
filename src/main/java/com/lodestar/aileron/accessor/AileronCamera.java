package com.lodestar.aileron.accessor;

import net.minecraft.world.entity.Entity;

public interface AileronCamera {
	double aileron$getPreviousEMAValue();

	void aileron$setPreviousEMAValue(float previousEMA);

	double aileron$getEMAValue();

	void aileron$setEMAValue(float EMA);

	float aileron$getSmoothedEMADifference(Entity entity, float partial);
}

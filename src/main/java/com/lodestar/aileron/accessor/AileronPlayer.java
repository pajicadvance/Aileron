package com.lodestar.aileron.accessor;

public interface AileronPlayer {
	boolean aileron$charged();

	int aileron$getBoostTicks();

	void aileron$setBoostTicks(int boostTicks);

	void aileron$setSmokeTrailTicks(int boostTicks);

	int aileron$getCampfireDamageIFrames();

	void aileron$setCampfireDamageIFrames(int campfireDamageIFrames);

	int aileron$getSmokestackCharges();

	void aileron$setSmokestackCharges(int charges);

	int aileron$getSmokestackCapacity();

	double aileron$getCloudskipperDrag();
}

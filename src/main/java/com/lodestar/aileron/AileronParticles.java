package com.lodestar.aileron;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

public class AileronParticles {

    public static final SimpleParticleType CUSTOM_CAMPFIRE_SMOKE = FabricParticleTypes.simple();

    public static void register() {
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Aileron.withModNamespace("custom_campfire_smoke"), CUSTOM_CAMPFIRE_SMOKE);
    }
}

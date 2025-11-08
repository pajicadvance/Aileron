package com.lodestar.aileron.client;

import com.lodestar.aileron.AileronParticles;
import com.lodestar.aileron.particle.CustomCampfireParticle;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class AileronClientParticles {

    public static void register() {
        ParticleFactoryRegistry.getInstance().register(
                AileronParticles.CUSTOM_CAMPFIRE_SMOKE,
                CustomCampfireParticle.CustomCampfireParticleProvider::new
        );
    }
}

package com.lodestar.aileron.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;

public class CustomCampfireParticle extends SingleQuadParticle {

    protected CustomCampfireParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, TextureAtlasSprite sprite) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed, sprite);
        this.scale(3.0F);
        this.setSize(0.25F, 0.25F);
        this.lifetime = this.random.nextInt(50) + (int) xSpeed;
        this.lifetime = this.random.nextInt(50) + (int) xSpeed;

        this.gravity = 3.0E-6F;
        this.xd = 0;
        this.yd = ySpeed + (double) (this.random.nextFloat() / 500.0F);
        this.zd = 0;

    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ < this.lifetime && !(this.alpha <= 0.0F)) {
            this.xd += (this.random.nextFloat() / 5000.0F * (float) (this.random.nextBoolean() ? 1 : -1));
            this.zd += (this.random.nextFloat() / 5000.0F * (float) (this.random.nextBoolean() ? 1 : -1));
            this.yd -= this.gravity;
            this.move(this.xd, this.yd, this.zd);
            if (this.age >= this.lifetime - 60 && this.alpha > 0.01F) {
                this.alpha -= 0.015F;
            }

        } else {
            this.remove();
        }
    }

    @Override
    protected @NotNull Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public record CustomCampfireParticleProvider(SpriteSet sprites) implements ParticleProvider<SimpleParticleType> {
        @Override
        public @NotNull Particle createParticle(@NotNull SimpleParticleType particleType, @NotNull ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, @NotNull RandomSource random) {
            CustomCampfireParticle campfireSmokeParticle = new CustomCampfireParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, sprites.get(random));
            campfireSmokeParticle.setAlpha(0.95F);
            return campfireSmokeParticle;
        }
    }
}

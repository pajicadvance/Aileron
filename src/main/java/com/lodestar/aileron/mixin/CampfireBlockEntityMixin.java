package com.lodestar.aileron.mixin;

import com.lodestar.aileron.Aileron;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(CampfireBlockEntity.class)
public class CampfireBlockEntityMixin {

	@Redirect(
            method = "particleTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/CampfireBlock;makeParticles(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;ZZ)V"
            )
    )
	private static void makeParticles(Level level, BlockPos pos, boolean isSignalFire, boolean smoking) {

		// check for neighboring campfires
		BlockPos[] possibleNeighbors = new BlockPos[]{
				pos.north(),
				pos.south(),
				pos.east(),
				pos.west()
		};

		int neighbors = 0;

		for (BlockPos neighbor : possibleNeighbors) {
			if (level.getBlockState(neighbor).getBlock() instanceof CampfireBlock) {
				neighbors++;
			}
		}

		if (neighbors > 0) {
			RandomSource random = level.getRandom();
			Optional<Holder.Reference<ParticleType<?>>> opt = BuiltInRegistries.PARTICLE_TYPE.get(Aileron.withModNamespace("custom_campfire_smoke"));
            if (opt.isPresent() && opt.get().value() instanceof SimpleParticleType particleType) {
                level.addAlwaysVisibleParticle(
                        particleType, true,
                        (double) pos.getX() + 0.5D + random.nextDouble() / 3.0D * (double) (random.nextBoolean() ? 1 : -1),
                        (double) pos.getY() + random.nextDouble() + random.nextDouble(),
                        (double) pos.getZ() + 0.5D + random.nextDouble() / 3.0D * (double) (random.nextBoolean() ? 1 : -1),
                        neighbors * 40 + (isSignalFire ? 280 : 80), 0.07D, 0.0D
                );
                if (smoking) {
                    level.addParticle(
                            ParticleTypes.SMOKE,
                            (double) pos.getX() + 0.5D + random.nextDouble() / 4.0D * (double) (random.nextBoolean() ? 1 : -1),
                            (double) pos.getY() + 0.4D,
                            (double) pos.getZ() + 0.5D + random.nextDouble() / 4.0D * (double) (random.nextBoolean() ? 1 : -1)
                            , 0.0D, 0.005D, 0.0D
                    );
                }
            }
		} else {
			CampfireBlock.makeParticles(level, pos, isSignalFire, smoking);
		}
	}
}

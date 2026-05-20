package com.lodestar.aileron;

import com.lodestar.aileron.accessor.AileronPlayer;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class Aileron implements ModInitializer {
    public static final String MOD_ID = "aileron";
    public static final Identifier CONFIG_RL = withModNamespace("config");
    public static AileronConfig CONFIG = ConfigApiJava.registerAndLoadConfig(AileronConfig::new);

    @Override
    public void onInitialize() {
        AileronAttributes.register();
        AileronLootModifiers.register();
        AileronNetworking.register();
        AileronParticles.register();
    }

    public static boolean canChargeSmokeStack(@Nullable Player player) {
        ItemStack elytra = getElytra(player);
        if (elytra.isEmpty()) {
            return false;
        }
        return player != null && ((AileronPlayer)player).aileron$getSmokestackCapacity() > 0 && !elytra.nextDamageWillBreak() && ((((AileronPlayer)player).aileron$getSmokestackCharges() > 0 && player.isFallFlying()) || player.isCrouching());
    }

    public static boolean canCampfireLaunch(@Nullable Player player) {
    ItemStack elytra = getElytra(player);
    return player != null && !elytra.isEmpty() && !elytra.nextDamageWillBreak();
}

    public static boolean isElytra(ItemStack stack) {
        return stack.has(DataComponents.GLIDER);
    }

    public static ItemStack getChestElytra(LivingEntity entity) {
        ItemStack chestItem = entity.getItemBySlot(EquipmentSlot.CHEST);
        if (isElytra(chestItem)) {
            return chestItem;
        }
        return ItemStack.EMPTY;
    }

    public static ItemStack getElytra(LivingEntity entity) {
        ItemStack stack = ItemStack.EMPTY;
        //if (CompatFlags.ACCESSORIES_LOADED) stack = AileronAccessoriesCompat.getAccessoryElytra(entity);
        if (stack.isEmpty()) {
            ItemStack chestItem = getChestElytra(entity);
            stack = (!chestItem.isEmpty()) ? chestItem : ItemStack.EMPTY;
        }
        return stack;
    }

    public static void boostPlayer(Player player) {
        if (player instanceof AileronPlayer ap) {
            ap.aileron$setBoostTicks(50);
        }
    }

    public static void playerDashedServer(ServerPlayer player) {
        ServerLevel serverLevel = player.level();
        int stocks = ((AileronPlayer)player).aileron$getSmokestackCharges();

        if (stocks > 0) {
            ((AileronPlayer)player).aileron$setSmokestackCharges(stocks - 1);

            sendBoostParticles(serverLevel, player.getX(), player.getY(), player.getZ());
            serverLevel.playSound(null, player.blockPosition(), SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 0.8f, 0.8f + (stocks * 0.2f));

            boostPlayer(player);
        }
    }

    public static void sendBoostParticles(ServerLevel serverLevel, double x, double y, double z) {
        for (ServerPlayer serverPlayer : serverLevel.players()) {
            serverLevel.sendParticles(serverPlayer, ParticleTypes.LARGE_SMOKE, true, false, x, y, z, 40, 0.5, 0.5, 0.5, 0.1);
            serverLevel.sendParticles(serverPlayer, ParticleTypes.CAMPFIRE_COSY_SMOKE, true, false, x, y, z, 40, 0.5, 0.5, 0.5, 0.1);
            serverLevel.sendParticles(serverPlayer, ParticleTypes.SMOKE, true, false, x, y, z, 120, 0.5, 0.5, 0.5, 0.4);
        }
    }

    public static Identifier withModNamespace(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}

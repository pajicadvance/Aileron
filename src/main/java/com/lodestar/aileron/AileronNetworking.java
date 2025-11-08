package com.lodestar.aileron;

import com.lodestar.aileron.payloads.SmokestackDashPayload;
import com.lodestar.aileron.payloads.SmokestackLaunchPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

public class AileronNetworking {

	public static final CustomPacketPayload.Type<SmokestackLaunchPayload> SMOKESTACK_LAUNCH_PACKET_ID =
			new CustomPacketPayload.Type<>(Aileron.withModNamespace("smokestack_launch"));
	public static final CustomPacketPayload.Type<SmokestackDashPayload> SMOKESTACK_DASH_PACKET_ID =
			new CustomPacketPayload.Type<>(Aileron.withModNamespace("smokestack_dash"));

    public static void sendSmokeStackLaunch(ServerPlayer player) {
        ServerPlayNetworking.send(player, new SmokestackLaunchPayload());
    }

    public static void register() {
        PayloadTypeRegistry.playS2C().register(SmokestackLaunchPayload.ID, SmokestackLaunchPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(SmokestackDashPayload.ID, SmokestackDashPayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(
                AileronNetworking.SMOKESTACK_DASH_PACKET_ID,
                (payload, context) -> Aileron.playerDashedServer(context.player())
        );
    }
}

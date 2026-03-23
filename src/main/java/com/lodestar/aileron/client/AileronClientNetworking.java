package com.lodestar.aileron.client;

import com.lodestar.aileron.AileronNetworking;
import com.lodestar.aileron.payloads.SmokestackDashPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class AileronClientNetworking {

    public static void sendSmokeStackDash() {
        ClientPlayNetworking.send(new SmokestackDashPayload());
    }

    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(
                AileronNetworking.SMOKESTACK_LAUNCH_PACKET_ID,
                (_, _) -> AileronClient.launchPlayer()
        );
    }
}

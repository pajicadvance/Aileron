package com.lodestar.aileron.client;

import com.lodestar.aileron.Aileron;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class AileronClientKeybinds {

    public static final KeyMapping.Category KEYBIND_CATEGORY = new KeyMapping.Category(Aileron.withModNamespace("keys"));

    public final static KeyMapping SMOKESTACK_BOOST = new KeyMapping(
            "key.aileron.smokestack_boost",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_SPACE,
            KEYBIND_CATEGORY
    );

    public static void register() {
        KeyMappingHelper.registerKeyMapping(AileronClientKeybinds.SMOKESTACK_BOOST);
    }
}

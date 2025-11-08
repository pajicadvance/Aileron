package com.lodestar.aileron.client;

import com.lodestar.aileron.Aileron;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
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
        KeyBindingHelper.registerKeyBinding(AileronClientKeybinds.SMOKESTACK_BOOST);
    }
}

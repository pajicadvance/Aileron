package com.lodestar.aileron;

import me.fzzyhmstrs.fzzy_config.util.EnumTranslatable;
import org.jetbrains.annotations.NotNull;

public enum FireworkUseBehavior implements EnumTranslatable {
    COSMETIC, NORMAL, DISABLE;

    @Override
    @NotNull public String prefix() {
        return "aileron.fireworkUseBehavior";
    }
}

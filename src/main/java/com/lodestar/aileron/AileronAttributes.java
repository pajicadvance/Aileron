package com.lodestar.aileron;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class AileronAttributes {

    public static final Holder<Attribute> CLOUDSKIPPER_DRAG = registerAttribute(
            "cloudskipper_drag",
            new RangedAttribute("attribute.aileron.cloudskipper_drag", 1.0, 0, 1.0)
                    .setSyncable(true).setSentiment(RangedAttribute.Sentiment.NEGATIVE)
    );

    public static final Holder<Attribute> SMOKESTACK_CAPACITY = registerAttribute(
            "smokestack_capacity",
            new RangedAttribute("attribute.aileron.smokestack_capacity", 0, 0, 1024)
                    .setSyncable(true)
    );

    public static Holder<Attribute> registerAttribute(String name, Attribute attribute) {
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, Aileron.withModNamespace(name), attribute);
    }

    public static void register() {
    }
}

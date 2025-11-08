package com.lodestar.aileron;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class AileronEnchantments {

	public static final ResourceKey<Enchantment> CLOUDSKIPPER = create("cloudskipper");
	public static final ResourceKey<Enchantment> SMOKESTACK = create("smokestack");

	private static ResourceKey<Enchantment> create(String name) {
		return ResourceKey.create(Registries.ENCHANTMENT, Aileron.withModNamespace(name));
	}
}

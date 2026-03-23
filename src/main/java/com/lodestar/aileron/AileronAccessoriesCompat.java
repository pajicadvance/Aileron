package com.lodestar.aileron;

/*
import io.wispforest.accessories.api.AccessoriesCapability;
import io.wispforest.accessories.api.slot.SlotEntryReference;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class AileronAccessoriesCompat {

    public static ItemStack getAccessoryElytra(LivingEntity entity) {
        Optional<AccessoriesCapability> ac = AccessoriesCapability.getOptionally(entity);
        if (ac.isPresent() && ac.get().isEquipped(stack -> stack.has(DataComponents.GLIDER))) {
            SlotEntryReference itemRef = ac.get().getFirstEquipped(stack -> stack.has(DataComponents.GLIDER));
            if (itemRef != null) return itemRef.stack();
        }
        return ItemStack.EMPTY;
    }
}
*/

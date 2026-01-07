package net.sikorski1.neoforgemod.util;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.sikorski1.neoforgemod.TutorialMod;
import net.sikorski1.neoforgemod.component.ModDataComponents;
import net.sikorski1.neoforgemod.item.ModItems;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        ItemProperties.register(ModItems.CHISEL.get(), ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID,
                "used"), (stack, level, entity, seed) -> stack.get(ModDataComponents.COORDINATES) != null ? 1f : 0f);

    }
}

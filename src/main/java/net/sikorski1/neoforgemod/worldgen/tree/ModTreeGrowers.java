package net.sikorski1.neoforgemod.worldgen.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.sikorski1.neoforgemod.TutorialMod;
import net.sikorski1.neoforgemod.worldgen.ModConfiguredFeatures;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower BLOODWOOD = new TreeGrower(TutorialMod.MOD_ID + ":bloodwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.BLOODWOOD_KEY), Optional.empty());
}

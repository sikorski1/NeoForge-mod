package net.sikorski1.neoforgemod.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sikorski1.neoforgemod.TutorialMod;
import net.sikorski1.neoforgemod.entity.custom.GeckoEntity;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, TutorialMod.MOD_ID);

    public static final Supplier<EntityType<GeckoEntity>> GECKO = ENTITY_TYPE.register("gecko",
            () -> EntityType.Builder.of(GeckoEntity::new, MobCategory.CREATURE).sized(0.75f, 0.35f).build("gecko"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPE.register(eventBus);
    }
}

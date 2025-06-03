package net.soldierzoom.misfortune.worldgen.tree;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.soldierzoom.misfortune.Misfortune;
import net.soldierzoom.misfortune.worldgen.tree.custom.MurkwoodFoliagePlacer;

public class ModFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, Misfortune.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<MurkwoodFoliagePlacer>> MURKWOOD_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("murkwood_foliage_placer", () -> new FoliagePlacerType<>(MurkwoodFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus) {
        FOLIAGE_PLACERS.register(eventBus);
    }
}

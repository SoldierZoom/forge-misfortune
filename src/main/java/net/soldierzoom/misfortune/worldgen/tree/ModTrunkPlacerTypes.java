package net.soldierzoom.misfortune.worldgen.tree;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.soldierzoom.misfortune.Misfortune;
import net.soldierzoom.misfortune.worldgen.tree.custom.MurkwoodTrunkPlacer;

public class ModTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, Misfortune.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<MurkwoodTrunkPlacer>> MURKWOOD_TRUNK_PLACER =
            TRUNK_PLACER.register("murkwood_trunk_placer", () -> new TrunkPlacerType<>(MurkwoodTrunkPlacer.CODEC));



    public static void register(IEventBus eventBus) {
        TRUNK_PLACER.register(eventBus);
    }
}

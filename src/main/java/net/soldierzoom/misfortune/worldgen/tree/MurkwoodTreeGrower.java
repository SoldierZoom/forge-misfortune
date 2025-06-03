package net.soldierzoom.misfortune.worldgen.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.soldierzoom.misfortune.worldgen.ModConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

public class MurkwoodTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean b) {
        return ModConfiguredFeatures.MURKWOOD_KEY;
    }
}

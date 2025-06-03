package net.soldierzoom.misfortune.worldgen.tree;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.soldierzoom.misfortune.worldgen.ModConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

public class MurkwoodTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean b) {
        return ModConfiguredFeatures.MURKWOOD_KEY;
    }

    @Override
    public boolean growTree(ServerLevel pLevel, ChunkGenerator pGenerator, BlockPos pPos, BlockState pState, RandomSource pRandom) {
        if(!areaIsValid(pLevel,pPos)) return false;
        return super.growTree(pLevel, pGenerator, pPos, pState, pRandom);
    }
    private boolean areaIsValid(ServerLevel pLevel, BlockPos centre) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                BlockPos soilPos = centre.offset(dx, -1, dz);
                BlockPos airPos = centre.offset(dx, 0, dz);

                BlockState soil = pLevel.getBlockState(soilPos);
                BlockState air = pLevel.getBlockState(airPos);

                if (!(soil.is(BlockTags.DIRT))) return false;
                if (dx == 0 && dz == 0) continue;
                if (!(air.isAir()||air.is(BlockTags.REPLACEABLE_BY_TREES))) return false;
            }
        }
        return true;
    }
}

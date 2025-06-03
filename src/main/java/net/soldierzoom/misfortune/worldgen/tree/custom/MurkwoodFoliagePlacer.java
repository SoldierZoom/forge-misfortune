package net.soldierzoom.misfortune.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.soldierzoom.misfortune.worldgen.tree.ModFoliagePlacers;

import static net.minecraft.world.level.levelgen.feature.TreeFeature.isAirOrLeaves;

public class MurkwoodFoliagePlacer extends FoliagePlacer {
    public static final Codec<MurkwoodFoliagePlacer> CODEC = RecordCodecBuilder.create(murkwoodFoliagePlacerInstance
            -> foliagePlacerParts(murkwoodFoliagePlacerInstance).and(Codec.intRange(0,16).fieldOf("height")
            .forGetter(fp -> fp.height)).apply(murkwoodFoliagePlacerInstance, MurkwoodFoliagePlacer::new));
    private final int height;



    public MurkwoodFoliagePlacer(IntProvider pRadius, IntProvider pOffset, int pHeight) {
        super(pRadius, pOffset);
        this.height=pHeight;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacers.MURKWOOD_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader pLevel, FoliageSetter pBlockSetter, RandomSource pRandom, TreeConfiguration pConfig, int pMaxFreeTreeHeight, FoliageAttachment pAttachment, int pFoliageHeight, int pFoliageRadius, int pOffset) {
        BlockPos pos = pAttachment.pos();
        //.radiusOffset has been repurposed to represent relative height from bottom of tree
        int minHangY = pos.getY() - pAttachment.radiusOffset() + 2;

        for (int y = 0; y < pFoliageHeight; y++) {
            int layerRadius = pFoliageRadius - y / 2;
            if (layerRadius < 0) continue;

            for (int dx = -layerRadius; dx <= layerRadius; dx++) {
                for (int dz = -layerRadius; dz <= layerRadius; dz++) {
                    int distance = Math.abs(dx) + Math.abs(dz);
                    if (distance > layerRadius + (pRandom.nextBoolean() ? 0 : -1)) continue;

                    BlockPos leafPos = pos.offset(dx, -y, dz);

                    if (isAirOrLeaves(pLevel, leafPos)) {
                        pBlockSetter.set(leafPos, pConfig.foliageProvider.getState(pRandom, leafPos));
                    }

                    // Stronger droopy effect: place vertical hanging leaves at edges
                    if (distance >= layerRadius - 1 && pRandom.nextFloat() < 0.6f) {
                        int maxHang = 1 + pRandom.nextInt(3); // 1 to 3 blocks hanging
                        BlockPos hangPos = leafPos.below();

                        for (int h = 0; h < maxHang; h++) {
                            if (hangPos.getY() < minHangY) break; // Stop if below minimum Y
                            if (isAirOrLeaves(pLevel, hangPos)) {
                                pBlockSetter.set(hangPos, pConfig.foliageProvider.getState(pRandom, hangPos));
                                hangPos = hangPos.below();
                            } else break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public int foliageHeight(RandomSource randomSource, int i, TreeConfiguration treeConfiguration) {
        return height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource randomSource, int i, int i1, int i2, int i3, boolean b) {
        return false;
    }
}

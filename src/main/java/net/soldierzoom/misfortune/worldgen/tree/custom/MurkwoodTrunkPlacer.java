package net.soldierzoom.misfortune.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.soldierzoom.misfortune.worldgen.tree.ModTrunkPlacerTypes;

import java.util.*;
import java.util.function.BiConsumer;

public class MurkwoodTrunkPlacer extends TrunkPlacer {
    public static final Codec<MurkwoodTrunkPlacer> CODEC = RecordCodecBuilder.create(murkwoodTrunkPlacerInstance ->
            trunkPlacerParts(murkwoodTrunkPlacerInstance).apply(murkwoodTrunkPlacerInstance, MurkwoodTrunkPlacer::new));
    public MurkwoodTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.MURKWOOD_TRUNK_PLACER.get();
    }


    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom, int treeHeight, BlockPos pBlockPos, TreeConfiguration pConfig) {

        List<FoliagePlacer.FoliageAttachment> attachments = new ArrayList<>();

        //N,E,S,W heights
        List<Integer> baseTrunkHeights = new ArrayList<>();
        baseTrunkHeights.add(3);
        baseTrunkHeights.add(1+pRandom.nextInt(2));// 1 or 2
        baseTrunkHeights.add(1+pRandom.nextInt(2));
        baseTrunkHeights.add((2+pRandom.nextInt(5))/2);// 2,3,4,5,6 -> 1 40%, 2 40%, or 3 20%
        //NE,SE,SW,NW heights
        List<Integer> baseCornerTrunkHeights = new ArrayList<>();
        baseCornerTrunkHeights.add(1);
        baseCornerTrunkHeights.add(1+pRandom.nextInt(2));
        baseCornerTrunkHeights.add(1+pRandom.nextInt(2));
        baseCornerTrunkHeights.add(pRandom.nextInt(2));
        //Shuffling
        Collections.shuffle(baseTrunkHeights,new Random(pRandom.nextLong()));
        Collections.shuffle(baseCornerTrunkHeights,new Random(pRandom.nextLong()));
        //Making sure baseTrunkHeights taller than neighbouring baseCornerTrunkHeights
        for(int i = 0;i<4;i++) {
            int h = baseTrunkHeights.get(i);
            int h1 = baseCornerTrunkHeights.get(i), h2 = baseCornerTrunkHeights.get((i+3)%4);
            int hMax = Math.max(h1, h2);
            if(h<=hMax) {
                baseTrunkHeights.set(i,hMax+1);
            }
        }

        //placing logs around base
        BlockPos[] baseTrunkPos = {
                pBlockPos.north(),pBlockPos.east(),pBlockPos.south(),pBlockPos.west(),
                pBlockPos.north().east(),pBlockPos.south().east(),pBlockPos.south().west(),pBlockPos.north().west()
        };
        for(int i=0;i<4;i++) {
            for(int y=0;y<3;y++) {
                if (baseTrunkHeights.get(i)>y) {
                    this.placeLog(pLevel,pBlockSetter,pRandom,baseTrunkPos[i].above(y),pConfig);
                }
                if (y < 2 && baseCornerTrunkHeights.get(i)>y) {
                    this.placeLog(pLevel,pBlockSetter,pRandom,baseTrunkPos[i+4].above(y),pConfig);
                }
            }
            setDirtAt(pLevel,pBlockSetter,pRandom,baseTrunkPos[i].below(),pConfig);
            //only placing dirt if log above
            if(baseCornerTrunkHeights.get(i)>0) {
                setDirtAt(pLevel,pBlockSetter,pRandom,baseTrunkPos[i+4].below(),pConfig);
            }
        }

        //placing 1x1 log until height
        setDirtAt(pLevel,pBlockSetter,pRandom,pBlockPos.below(),pConfig);
        for (int y = 0; y < treeHeight; y++) {
            this.placeLog(pLevel,pBlockSetter,pRandom,pBlockPos.above(y),pConfig);
        }

        //Creating branches
        List<Direction> dirs = new ArrayList<>(Arrays.asList(Direction.Plane.HORIZONTAL.stream().toArray(Direction[]::new)));
        Collections.shuffle(dirs,new Random(pRandom.nextLong()));

        BlockPos trunkTop = pBlockPos.above(treeHeight-3);
        for (int i = 0; i < 1 + pRandom.nextInt(3); i++) {
            //intialising vars for loop
            int l=0, branchLength = 2 + pRandom.nextInt(3);//2-4
            Direction dir = dirs.get(i);
            Direction.Axis axis = dir.getAxis();
            BlockPos currentPos = trunkTop;
            //relative height from base of tree
            int h=currentPos.getY() - pBlockPos.below().getY();

            currentPos = currentPos.relative(dir);
            pBlockSetter.accept(currentPos, pConfig.trunkProvider.getState(pRandom, currentPos).setValue(BlockStateProperties.AXIS, axis));
            attachments.add(new FoliagePlacer.FoliageAttachment(currentPos.above(), 0, false));
            l++;
            while (l<branchLength) {
                //horizontal
                for(int j=0;j<(1+pRandom.nextInt(3));j++) {
                    currentPos = currentPos.relative(dir);
                    pBlockSetter.accept(currentPos, pConfig.trunkProvider.getState(pRandom, currentPos).setValue(BlockStateProperties.AXIS, axis));
                    attachments.add(new FoliagePlacer.FoliageAttachment(currentPos.above(), h, false));
                    l++;
                }
                attachments.remove(new FoliagePlacer.FoliageAttachment(currentPos.above(), h, false));
                //vertical
                currentPos = currentPos.above();
                pBlockSetter.accept(currentPos, pConfig.trunkProvider.getState(pRandom, currentPos).setValue(BlockStateProperties.AXIS, Direction.Axis.Y));
                h++;
            }
            attachments.add(new FoliagePlacer.FoliageAttachment(currentPos.above(), h, false));
        }

        // Add foliage at the top
        attachments.add(new FoliagePlacer.FoliageAttachment(pBlockPos.above(treeHeight), treeHeight, false));
        return attachments;//NOTE .radiusOffset in attachments has been repurposed as relative height from base
    }
}

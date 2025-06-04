package net.soldierzoom.misfortune.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.soldierzoom.misfortune.Misfortune;
import net.soldierzoom.misfortune.block.ModBlocks;
import net.soldierzoom.misfortune.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Misfortune.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //minecraft tags
        this.tag(BlockTags.LOGS)
                .addTag(ModTags.Blocks.MURKWOOD_LOGS);
        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.MURKWOOD_PLANKS.get());
        this.tag(BlockTags.LEAVES)
                .add(ModBlocks.MURKWOOD_LEAVES.get());
        this.tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.MURKWOOD_DOOR.get());
        this.tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.MURKWOOD_STAIRS.get());
        this.tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.MURKWOOD_SLAB.get());
        this.tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.MURKWOOD_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.MURKWOOD_FENCE_GATE.get());
        //mod tags
        this.tag(ModTags.Blocks.MURKWOOD_LOGS)
                .add(ModBlocks.MURKWOOD_LOG.get())
                .add(ModBlocks.MURKWOOD_WOOD.get())
                .add(ModBlocks.STRIPPED_MURKWOOD_LOG.get())
                .add(ModBlocks.STRIPPED_MURKWOOD_WOOD.get());
    }
}

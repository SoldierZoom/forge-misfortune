package net.soldierzoom.misfortune.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.tags.ITag;
import net.soldierzoom.misfortune.Misfortune;
import net.soldierzoom.misfortune.block.ModBlocks;
import net.soldierzoom.misfortune.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider,
                               CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, Misfortune.MOD_ID,existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //copying block tags
        //minecraft tags
        copy(BlockTags.LOGS,ItemTags.LOGS);
        copy(BlockTags.PLANKS,ItemTags.PLANKS);
        copy(BlockTags.WOODEN_STAIRS,ItemTags.WOODEN_STAIRS);
        copy(BlockTags.WOODEN_SLABS,ItemTags.WOODEN_SLABS);
        copy(BlockTags.WOODEN_FENCES,ItemTags.WOODEN_FENCES);
        copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);
        copy(BlockTags.WOODEN_DOORS,ItemTags.WOODEN_DOORS);
        copy(BlockTags.WOODEN_TRAPDOORS,ItemTags.WOODEN_TRAPDOORS);
        copy(BlockTags.WOODEN_PRESSURE_PLATES,ItemTags.WOODEN_PRESSURE_PLATES);
        copy(BlockTags.WOODEN_BUTTONS,ItemTags.WOODEN_BUTTONS);

        copy(BlockTags.LEAVES,ItemTags.LEAVES);
        copy(BlockTags.SAPLINGS,ItemTags.SAPLINGS);
        //mod tags
        copy(ModTags.Blocks.MURKWOOD_LOGS,ModTags.Items.MURKWOOD_LOGS);
    }
}

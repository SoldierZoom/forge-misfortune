package net.soldierzoom.misfortune.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
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
        copy(BlockTags.LEAVES,ItemTags.LEAVES);
        copy(BlockTags.WOODEN_DOORS,ItemTags.WOODEN_DOORS);
        copy(BlockTags.WOODEN_STAIRS,ItemTags.WOODEN_STAIRS);
        //mod tags
        copy(ModTags.Blocks.MURKWOOD_LOGS,ModTags.Items.MURKWOOD_LOGS);
    }
}

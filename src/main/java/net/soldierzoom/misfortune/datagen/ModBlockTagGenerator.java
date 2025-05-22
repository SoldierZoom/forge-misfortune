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
                .addTag(ModTags.Blocks.AURUM_LOGS);
        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.AURUM_PLANKS.get())
                .add(ModBlocks.CAERULEUM_PLANKS.get());
        //mod tags
        this.tag(ModTags.Blocks.AURUM_LOGS)
                .add(ModBlocks.AURUM_LOG.get())
                .add(ModBlocks.AURUM_WOOD.get());
    }
}

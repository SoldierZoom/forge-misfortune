package net.soldierzoom.misfortune.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import net.soldierzoom.misfortune.Misfortune;
import net.soldierzoom.misfortune.block.ModBlocks;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    private static final DeferredRegister<Block> DROP_SELF = DeferredRegister.create(ForgeRegistries.BLOCKS, Misfortune.MOD_ID);

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        //aurum wood
        this.dropSelf(ModBlocks.AURUM_LOG.get());
        this.dropSelf(ModBlocks.AURUM_WOOD.get());
        this.dropSelf(ModBlocks.AURUM_PLANKS.get());
        //caeruleum wood
        this.dropSelf(ModBlocks.CAERULEUM_LOG.get());
        this.dropSelf(ModBlocks.CAERULEUM_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_CAERULEUM_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_CAERULEUM_WOOD.get());
        this.dropSelf(ModBlocks.CAERULEUM_PLANKS.get());
        this.add(ModBlocks.CAERULEUM_LEAVES.get(), block ->
                createLeavesDrops(block,ModBlocks.AURUM_PLANKS.get(), NORMAL_LEAVES_SAPLING_CHANCES));//TODO: CHANGE TO SAPLING
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
